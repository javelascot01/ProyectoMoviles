plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.google.gms.google.services)
}

android {
    androidResources {
        generateLocaleConfig = true
    }
    namespace = "com.javt.proyectojesusvelasco"
    compileSdk = 35
    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.javt.proyectojesusvelasco"
        minSdk = 34
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
}

dependencies {
    implementation (libs.play.services.identity)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    implementation("com.google.android.material:material:1.12.0")
    implementation (libs.firebase.auth)
    implementation(libs.play.services.auth)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.preference)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.espresso.core)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.identity.credential)
    implementation(libs.play.services.fido)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}