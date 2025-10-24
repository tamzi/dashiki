plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
    alias(libs.plugins.dashiki.android.application.printing)
}

android {
    namespace = "com.tamzi.ddscatalog"

    defaultConfig {
        applicationId = "com.tamzi.ddscatalog"
        versionCode = 1
        versionName = "1.0"

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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":dds"))
}
