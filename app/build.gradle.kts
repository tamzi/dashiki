import org.jetbrains.kotlin.gradle.internal.builtins.StandardNames.FqNames.unit

plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
    alias(libs.plugins.dashiki.android.application.flavors)
    alias(libs.plugins.dashiki.android.application.printing)
    // Testing conventions (apply only if module has tests)
    id("dashiki.android.unit.test")
    id("dashiki.android.instrumented.test")
    id("dashiki.android.compose.instrumented.test")
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
    implementation(project(":dds"))
}
