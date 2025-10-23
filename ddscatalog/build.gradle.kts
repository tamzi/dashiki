plugins {
    alias(libs.plugins.dashiki.android.application)
    alias(libs.plugins.dashiki.android.application.compose)
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.ui.tooling.preview)

    implementation(project(":dds"))

    //Koin
    implementation(libs.koin.androidx.compose)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Koin for Tests
    testImplementation(libs.koin.test.junit4)
}
