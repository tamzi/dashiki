import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tamzi.dashiki.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    implementation(libs.compose.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "dashiki.android.application.compose"
            implementationClass = "application.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "dashiki.android.application"
            implementationClass = "application.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "dashiki.android.library.compose"
            implementationClass = "library.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "dashiki.android.library"
            implementationClass = "library.AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "dashiki.android.feature"
            implementationClass = "feature.AndroidFeatureConventionPlugin"
        }
        register("androidTest") {
            id = "dashiki.android.test"
            implementationClass = "test.AndroidTestConventionPlugin"
        }
        register("hilt") {
            id = "dashiki.hilt"
            implementationClass = "hilt.HiltConventionPlugin"
        }
        register("androidRoom") {
            id = "dashiki.android.room"
            implementationClass = "android.AndroidRoomConventionPlugin"
        }
        register("androidFirebase") {
            id = "dashiki.android.application.firebase"
            implementationClass = "application.AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidLint") {
            id = "dashiki.android.lint"
            implementationClass = "android.AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = "dashiki.jvm.library"
            implementationClass = "jvm.JvmLibraryConventionPlugin"
        }
        register("androidApplicationFlavors") {
            id = "dashiki.android.application.flavors"
            implementationClass = "flavor.AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidApplicationPrinting") {
            id = "dashiki.android.application.printing"
            implementationClass = "printing.AndroidApplicationPrintingConventionPlugin"
        }
    }
}
