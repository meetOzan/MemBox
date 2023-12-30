@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.mertozan.membox.login"
    compileSdk = libs.versions.sdkCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdkMin.get().toInt()

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
    buildFeatures{
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
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

    implementation(project(":core:presentation"))
    implementation(project(":core:localization"))

    // Core
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecyle)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.bundles.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.compose.viewbinding)
    implementation(libs.androidx.fragment)

    // Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.androidTestImplementation)
    debugImplementation(libs.bundles.debugTestImplementation)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}