plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //Google services Graddle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.gestiontaller"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gestiontaller"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Library for Retrofit 2
    implementation(libs.retrofit)
    //GSON library for handling and converting JSON data
    implementation(libs.converter.gson)
    //Library OkHttp for simplify construction of HTTP requests
    implementation(libs.okhttp)
    //Library for use coroutines in Kotlin (HTTP requests in background)
    implementation(libs.kotlinx.coroutines.android)
    //Library Picasso for can use to show images
    implementation(libs.picasso)

    implementation(libs.kotlin.stdlib)


    //Library for use more icons
    implementation(libs.icons)

    //Import the FireBase BoM
    implementation(platform(libs.firebase.bom))

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    implementation(libs.androidx.navigation.fragment.ktx.v240)
    implementation(libs.androidx.navigation.ui.ktx.v240)

    implementation (libs.androidx.viewpager2)
    implementation (libs.material.v140)

    //Add new library for use the Chip Bottom Navigation Bar
    implementation(libs.ismaeldivita.chip.navigation.bar)

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries

    //Default libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}