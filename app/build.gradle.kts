@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.example.geoquiz"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.geoquiz"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":library_base")))
    implementation(project(mapOf("path" to ":library_http")))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.activityGroup)
    implementation(libs.bundles.fragmentGroup)
    implementation(libs.bundles.navgationGroup)
    implementation(libs.core.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.databinding.runtime)
    implementation(libs.bundles.roomGroup)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    ksp(libs.room.ksp)
    implementation(libs.bundles.lifecycleGroup)
    implementation(libs.androidAutoSize)
    implementation(libs.bundles.smartRefreshGroup)
    implementation(libs.util.code)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}