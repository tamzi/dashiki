plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
    alias(libs.plugins.dashiki.android.application.flavors)
    alias(libs.plugins.dashiki.android.application.printing)
}

android {
    namespace = "com.tamzi.dashiki"

    defaultConfig {
        applicationId = "com.tamzi.dashiki"
        versionCode = 1
        versionName = "0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material.icons.extended) // Material Icons Extended
    implementation(libs.material) // Material Components for themes
    // ConstraintLayout for Compose
    implementation(libs.androidx.constraintlayout.compose)

    implementation(project(":dds"))

    // Android instrumented tests
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
