@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.androidx.navigation.safe.args)
}

android {
    namespace = "com.mertozan.membox"
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
        kotlinCompilerExtensionVersion = libs.versions.composeVer.get()
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
    implementation(project(":core:domain"))

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
    implementation(project(mapOf("path" to ":core:common")))
    implementation(project(":core:model"))

    // Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.androidTestImplementation)
    debugImplementation(libs.bundles.debugTestImplementation)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Navigation
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose.viewmodel)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.hilt.compiler)

    // Coil
    implementation(libs.coil.ktx)

    // Lottie
    implementation(libs.com.airbnb.android.lottie)
}