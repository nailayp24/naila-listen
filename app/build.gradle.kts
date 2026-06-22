plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.naila_listen"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.naila_listen"
        minSdk = 24
        targetSdk = 36
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // === LIBRARIES BAWAAN UTAMA ===
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.gridlayout:gridlayout:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // === INTEGRASI REST API (RETROFIT & COROUTINES) ===
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.kotlinx.coroutines.android)

    // === INTEGRASI PENYIMPANAN LOKAL ROOM DATABASE ===
    val room_version = "2.7.0-alpha12"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    // 🌟 === LIBRARY INTEGRASI BARU PERTEMUAN 13 (KAMERA & QR) === 🌟
    // ZXing Core Engine (Generator Pembuat QR Code)
    implementation("com.google.zxing:core:3.5.2")

    // Android Jetpack CameraX Core Components Ecosystem
    implementation("androidx.camera:camera-camera2:1.3.3")
    implementation("androidx.camera:camera-lifecycle:1.3.3")
    implementation("androidx.camera:camera-view:1.3.3")

    // Google ML Kit Vision Barcode Scanning API (Mesin Scan QR Offline)
    implementation("com.google.mlkit:barcode-scanning:17.2.0")

    // === UI COMPONENTS ===
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // === TESTING LIBRARIES ===
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}