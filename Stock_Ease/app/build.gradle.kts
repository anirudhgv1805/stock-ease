plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.anirudhgv.stockease"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.anirudhgv.stockease"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.recyclerview)

    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel.ktx)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.multidex)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.network)
    implementation(libs.jmdns)

    implementation(libs.swiperefreshlayout)

    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
}