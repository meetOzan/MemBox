@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // The google-services Gradle plugin needs to be applied on a project with com.android.application
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.mertozan.membox"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        applicationId = "com.mertozan.membox"
        minSdk = libs.versions.sdkMin.get().toInt()
        targetSdk = libs.versions.sdkTarget.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeVer.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":feature:login"))
    implementation(project(":core:presentation"))
    implementation(project(":core:localization"))
    implementation(project(":core:data"))
    implementation(project(":navigation"))

    // Core
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecyle)
    implementation(libs.bundles.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.material)
    implementation(libs.bundles.material)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    // Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.androidTestImplementation)
    debugImplementation(libs.bundles.debugTestImplementation)

    // Firebase Auth
    implementation(libs.firebase.auth)

    // Firebase Firestore
    implementation(libs.firebase.firestore)

    // Firebase Storage
    implementation(libs.firebase.storage)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.hilt.navigation.compose)
}
