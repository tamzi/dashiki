import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.tamzi.mboga.buildlogic"

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
    compileOnly(libs.compose.gradlePlugin)
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
            id = "mboga.android.application.compose"
            implementationClass = "application.AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "mboga.android.application"
            implementationClass = "application.AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "mboga.android.library.compose"
            implementationClass = "library.AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "mboga.android.library"
            implementationClass = "library.AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "mboga.android.feature"
            implementationClass = "feature.AndroidFeatureConventionPlugin"
        }
        register("androidTest") {
            id = "mboga.android.test"
            implementationClass = "test.AndroidTestConventionPlugin"
        }
        register("hilt") {
            id = "mboga.hilt"
            implementationClass = "hilt.HiltConventionPlugin"
        }
        register("androidRoom") {
            id = "mboga.android.room"
            implementationClass = "android.AndroidRoomConventionPlugin"
        }
        register("androidFirebase") {
            id = "mboga.android.application.firebase"
            implementationClass = "application.AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidLint") {
            id = "mboga.android.lint"
            implementationClass = "android.AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = "mboga.jvm.library"
            implementationClass = "jvm.JvmLibraryConventionPlugin"
        }
    }
}
