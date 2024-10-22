plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.logintask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.logintask"
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

    // Google Sign-In
    implementation(libs.play.services.auth)


    // Facebook Login SDK
    implementation(libs.facebook.login)

    // Microsoft Authentication Library (MSAL)
    implementation(libs.msal) {
        exclude(group = "com.microsoft.device.display", module = "display-mask")
    }
}