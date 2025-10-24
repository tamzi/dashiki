plugins {
    alias(libs.plugins.dashiki.android.library)
    alias(libs.plugins.dashiki.android.library.compose)
}

android {
    namespace = "com.tamzi.dds"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    // Core Android dependencies exposed to consumers
    api(libs.androidx.core.ktx)
    api(libs.androidx.activity.compose)
    api(libs.androidx.lifecycle.runtime.ktx)

    // Compose UI dependencies exposed to consumers
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidxComposeUi)
    api(libs.androidxComposeUiGraphics)

    api(libs.androidx.compose.animation)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidxComposeUiTest)

    debugImplementation(libs.androidxComposeUiTestManifest)
}