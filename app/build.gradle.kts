plugins {

    id("com.android.application")

    id("org.jetbrains.kotlin.android")

    id("org.jetbrains.kotlin.plugin.compose")

    id("com.google.dagger.hilt.android")

    kotlin("kapt")
}


android {

    namespace = "com.example.smartmeetingai"

    compileSdk = 35


    defaultConfig {

        applicationId = "com.example.smartmeetingai"

        minSdk = 26

        targetSdk = 35

        versionCode = 1

        versionName = "1.0"

    }


    buildFeatures {

        compose = true
        mlModelBinding = true

    }


    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17

        targetCompatibility = JavaVersion.VERSION_17

    }


    kotlinOptions {

        jvmTarget = "17"

    }

}



dependencies {


    // Compose

    implementation(
        platform(
            "androidx.compose:compose-bom:2024.10.00"
        )
    )


    implementation(
        "androidx.activity:activity-compose:1.9.3"
    )


    implementation(
        "androidx.compose.material3:material3"
    )


    implementation(
        "androidx.compose.ui:ui"
    )


    implementation(
        "androidx.compose.ui:ui-tooling-preview"
    )


    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )


    // Navigation

    implementation(
        "androidx.navigation:navigation-compose:2.8.3"
    )


    // ViewModel

    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6"
    )


    // Coroutines

    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0"
    )


    // Room

    implementation(
        "androidx.room:room-runtime:2.6.1"
    )


    implementation(
        "androidx.room:room-ktx:2.6.1"
    )


    kapt(
        "androidx.room:room-compiler:2.6.1"
    )


    // Hilt

    implementation(
        "com.google.dagger:hilt-android:2.52"
    )


    kapt(
        "com.google.dagger:hilt-compiler:2.52"
    )

    // Hilt Navigation for Compose
    implementation(
        "androidx.hilt:hilt-navigation-compose:1.2.0"
    )

    // WorkManager

    implementation(
        "androidx.work:work-runtime-ktx:2.9.1"
    )

    // TensorFlow Lite for on-device inference
    implementation(
        "org.tensorflow:tensorflow-lite:2.14.0",
    )

    implementation(
        "org.tensorflow:tensorflow-lite-support:0.4.4",
    )

    implementation(
        "org.tensorflow:tensorflow-lite-metadata:0.4.4",
    )

    // Text classification with TFLite
    implementation(
        "org.tensorflow:tensorflow-lite-task-text:0.4.4",
    )

    // Testing
    testImplementation(
        "junit:junit:4.13.2",
    )
}