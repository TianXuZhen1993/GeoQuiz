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
    implementation(libs.bundles.activity.group)
    implementation(libs.bundles.fragment.group)
    implementation(libs.core.ktx)
    implementation(libs.constraintlayout)
    implementation(libs.databinding.runtime)
    implementation(libs.bundles.room.group)
    ksp(libs.room.ksp)
    implementation(libs.bundles.lifecycle.group)
    implementation(libs.androidAutoSize)
    implementation(libs.util.code)
    implementation(libs.bundles.immersionbar.group)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}