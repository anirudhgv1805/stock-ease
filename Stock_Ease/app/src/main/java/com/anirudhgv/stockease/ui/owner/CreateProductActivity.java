package com.anirudhgv.stockease.ui.owner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anirudhgv.stockease.R;
import com.anirudhgv.stockease.data.model.Product;
import com.anirudhgv.stockease.data.model.dto.ImageUploadResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class CreateProductActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private Uri selectedImageUri;
    private String uploadedImageUrl;
    private ImageView imagePreview;

    private TextInputEditText nameInput, descInput, skuInput, priceInput;
    private MaterialButton createBtn, selectImageBtn;

    private static final int IMAGE_PICK_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_product);

        nameInput = findViewById(R.id.productNameInput);
        descInput = findViewById(R.id.productDescriptionInput);
        skuInput = findViewById(R.id.productSkuInput);
        priceInput = findViewById(R.id.productPriceInput);
        createBtn = findViewById(R.id.createProductButton);
        imagePreview = findViewById(R.id.productImagePreview);
        selectImageBtn = findViewById(R.id.selectImageButton);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        selectImageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Product Image"), IMAGE_PICK_CODE);
        });

        createBtn.setOnClickListener(view -> {
            String name = nameInput.getText().toString().trim();
            String desc = descInput.getText().toString().trim();
            String sku = skuInput.getText().toString().trim();
            String priceStr = priceInput.getText().toString().trim();

            if (name.isEmpty() || desc.isEmpty() || sku.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (uploadedImageUrl == null) {
                Toast.makeText(this, "Please upload an image first", Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal price;
            try {
                price = new BigDecimal(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(name, desc, sku, price);
            product.setProductImgUrl(uploadedImageUrl);

            productViewModel.createProduct(product).observe(this, success -> {
                if (success) {
                    Toast.makeText(this, "Product created successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to create product", Toast.LENGTH_LONG).show();
                }
            });
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
            Toast.makeText(this, "Failed to process image", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(CreateProductActivity.this, "Image uploaded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateProductActivity.this, "Image upload failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ImageUploadResponse> call, Throwable t) {
                Toast.makeText(CreateProductActivity.this, "Upload error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private File createTempFileFromUri(Context context, Uri uri) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = "upload_" + System.currentTimeMillis();
        File tempFile = File.createTempFile(fileName, ".jpg", context.getCacheDir());

        try (OutputStream outStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }

        return tempFile;
    }

    public interface ImageUploadService {
        @Multipart
        @POST("upload/ ")
        Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part file);
    }
}
