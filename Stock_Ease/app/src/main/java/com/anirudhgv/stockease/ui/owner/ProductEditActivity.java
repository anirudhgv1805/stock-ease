package com.anirudhgv.stockease.ui.owner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.dto.ImageUploadResponse;
import com.anirudhgv.stockease.data.network.ApiClient;
import com.anirudhgv.stockease.data.network.ApiService;
import com.anirudhgv.stockease.data.storage.SessionManager;
import com.bumptech.glide.Glide;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.*;
import retrofit2.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class ProductEditActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 102;

    private Spinner spinnerProduct;
    private EditText editName, editSku, editPrice;
    private Button btnUpdate, btnSelectImage;
    private ImageView imagePreview;

    private List<Product> productList;
    private Product selectedProduct;
    private Uri selectedImageUri;
    private String uploadedImageUrl;

    private ApiService apiService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_product_edit);

        spinnerProduct = findViewById(R.id.spinnerProduct);
        editName = findViewById(R.id.editName);
        editSku = findViewById(R.id.editSku);
        editPrice = findViewById(R.id.editPrice);
        btnUpdate = findViewById(R.id.btnUpdateProduct);
        btnSelectImage = findViewById(R.id.selectImageButton);
        imagePreview = findViewById(R.id.productImagePreview);

        sessionManager = new SessionManager(this);
        apiService = ApiClient.getApiService(sessionManager);

        loadProducts();

        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = productList.get(position);
                editName.setText(selectedProduct.getName());
                editSku.setText(selectedProduct.getSku());
                editPrice.setText(String.valueOf(selectedProduct.getPrice()));

                String imageUrl = selectedProduct.getProductImgUrl();
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    Glide.with(getApplicationContext())
                            .load(imageUrl)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_background)
                            .into(imagePreview);
                }

                if (selectedProduct.getProductImgUrl() != null) {
                    Glide.with(ProductEditActivity.this)
                            .load(selectedProduct.getProductImgUrl())
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(imagePreview);
                }

                uploadedImageUrl = selectedProduct.getProductImgUrl();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnSelectImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Image"), IMAGE_PICK_CODE);
        });

        btnUpdate.setOnClickListener(v -> {
            v.setEnabled(false);
            selectedProduct.setName(editName.getText().toString().trim());
            selectedProduct.setSku(editSku.getText().toString().trim());
            selectedProduct.setPrice(BigDecimal.valueOf(Double.parseDouble(editPrice.getText().toString())));
            selectedProduct.setProductImgUrl(uploadedImageUrl);
            selectedProduct.setCreatedAt(LocalDateTime.now());

            apiService.updateProduct(selectedProduct.getId(), selectedProduct)
                    .enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            v.setEnabled(true);
                            if (response.isSuccessful()) {
                                Toast.makeText(ProductEditActivity.this, "Product updated!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ProductEditActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            v.setEnabled(true);
                            Toast.makeText(ProductEditActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void loadProducts() {
        apiService.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ProductEditActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            productList.stream().map(Product::getSku).collect(Collectors.toList()));
                    spinnerProduct.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imagePreview.setImageURI(selectedImageUri);
            uploadImageToCdn(selectedImageUri);
        }
    }

    private void uploadImageToCdn(Uri imageUri) {
        Toast.makeText(this, "Uploading image...", Toast.LENGTH_SHORT).show();

        File file;
        try {
            file = createTempFileFromUri(this, imageUri);
        } catch (IOException e) {
            Toast.makeText(this, "Image file error", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestBody reqFile = RequestBody.create(file, MediaType.parse("image/*"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cdn.local/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageUploadService service = retrofit.create(ImageUploadService.class);
        service.uploadImage(body).enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    uploadedImageUrl = response.body().getUrl();
                    Toast.makeText(ProductEditActivity.this, "Image uploaded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductEditActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                Toast.makeText(ProductEditActivity.this, "Upload error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private File createTempFileFromUri(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        File tempFile = File.createTempFile("upload_" + System.currentTimeMillis(), ".jpg", context.getCacheDir());

        try (OutputStream outStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, len);
            }
        }
        return tempFile;
    }

    public interface ImageUploadService {
        @Multipart
        @POST("upload/")
        Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part file);
    }
}
