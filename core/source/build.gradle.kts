@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.mertozan.membox.source"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}


dependencies {

    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:localization"))

    // Core
    implementation(libs.androidx.core)
    implementation(libs.androidx.compat)
    implementation(libs.material)

    // Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.bundles.androidTestImplementation)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.hilt.compiler)

    // Firebase
    implementation(platform(libs.firebase.bom))

    // Firebase Auth
    implementation(libs.firebase.auth)

    // Firebase Firestore
    implementation(libs.firebase.firestore)

    // Firebase Storage
    implementation(libs.firebase.storage)

    // Room
    kapt(libs.room.kapt)
    implementation(libs.androidx.room)
    annotationProcessor(libs.annotation.room.compiler)
}