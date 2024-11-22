plugins {
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("org.jetbrains.kotlin.plugin.serialization") version "2.0.10"
    //alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.google.firebase.firebase.perf)
}

android {
    namespace = "com.mwafaimk.unify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mwafaimk.unify"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.9"

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
            signingConfig = signingConfigs.getByName("debug")
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.volley)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.config)
    implementation(libs.firebase.perf)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.retrofit)
    implementation (libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation (libs.logging.interceptor)
    // AppCompat and Material Design
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.tracing.perfetto.handshake)

    // DataStore
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore.preferences.rxjava3)
    implementation(libs.androidx.datastore.preferences.rxjava2)

    // Kotlin Coroutines
    implementation (libs.kotlinx.coroutines.android.v181)
    implementation (libs.kotlinx.coroutines.core)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Retrofit2 Kotlin Coroutines Adapter
    implementation(libs.retrofit2.kotlin.coroutines.adapter)

    // Android Lifecycle Extensions
    implementation(libs.androidx.lifecycle.extensions)

    // Lifecycle Compiler (Common Java 8 support)
    kapt(libs.androidx.lifecycle.common.java8)

    //Room Database
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // RxJava and RxAndroid
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    //Kotlin Metadata
    kapt(libs.kotlinx.metadata.jvm)

    // JavaPoet
    implementation(libs.javapoet)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Hilt for ViewModel Injection
    kapt(libs.androidx.hilt.compiler)

    // Hilt for Navigation in Compose
    implementation(libs.androidx.hilt.navigation.compose)

    implementation (libs.androidx.material.icons.extended)
    //implementation (libs.androidx.material.icons.extended)

    implementation(libs.accompanist.systemuicontroller)
    implementation(kotlin("script-runtime"))

    implementation(libs.google.accompanist.swiperefresh)

    // encryption
    implementation(libs.androidx.security.crypto)

}

kapt {
    correctErrorTypes = true
}
