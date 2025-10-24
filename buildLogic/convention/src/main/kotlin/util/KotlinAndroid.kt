package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * This file defines functions to configure Kotlin settings for both Android and JVM
 * projects within a Gradle build script.
 *
 * These configurations ensure that the projects adhere to specific standards and use
 * the appropriate versions of Java and Kotlin.
 *
 * Configure base Kotlin with Android options.
 * This setup ensures that the project is configured with the necessary Kotlin and Android options,
 * including support for Java 11 APIs and core library desugaring.
 *
 * The configureKotlinAndroid function is an internal extension function
 * for the Project class in Gradle, designed to configure Kotlin and Android-specific options for a project.
 * It takes a CommonExtension parameter, which represents the common configuration options for Android projects.
 * Within the function, the commonExtension is configured to set
 * the compileSdk version to 34 and the minSdk version to 21 in the defaultConfig block:
 *
 *  `compileSdk = 34
 *
 *  defaultConfig {
 *     minSdk = 21
 *  }`
 *
 *  The compileOptions block is configured to set the sourceCompatibility and targetCompatibility
 *  to Java 11. Additionally, it enables core library desugaring, which allows the use of Java 11 APIs
 *  on older Android versions:
 *
 *  `compileOptions {
 *     sourceCompatibility = JavaVersion.VERSION_11
 *     targetCompatibility = JavaVersion.VERSION_11
 *     isCoreLibraryDesugaringEnabled = true
 * `}`
 *
 * The function then calls `configureKotlin` with `KotlinAndroidProjectExtension`
 * to apply additional Kotlin-specific configurations.
 * This is done using a generic function that configures Kotlin options based on the project type:
 *
 * `configureKotlin<KotlinAndroidProjectExtension>()`
 *
 * Finally, the function adds a dependency for core library desugaring using the dependencies block.
 * The dependencies block is used to add the coreLibraryDesugaring dependency to the project.
 * It retrieves the library from the version catalog and adds it to the project:
 *
 *  `dependencies {
 *     add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
 *  }`
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = BuildLogicConstants.COMPILE_SDK

        defaultConfig {
            minSdk = BuildLogicConstants.MIN_SDK
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JavaVersion.toVersion(BuildLogicConstants.JAVA_VERSION)
            targetCompatibility = JavaVersion.toVersion(BuildLogicConstants.JAVA_VERSION)
            isCoreLibraryDesugaringEnabled = true
        }
    }

    // Configure Kotlin options via tasks
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(BuildLogicConstants.KOTLIN_JVM_TARGET))
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }

    dependencies {
        add("coreLibraryDesugaring",
            project.extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary("android.desugarJdkLibs").get())

        // Core Android KTX dependency (used in virtually all Android modules)
        add(
            "implementation",
            project.extensions.getByType<VersionCatalogsExtension>().named("libs")
                .findLibrary("androidx.core.ktx").get()
        )
    }
}

/**
 *
 * Configure base Kotlin options for JVM (non-Android)
 *
 * This function configures Kotlin options for JVM (non-Android) projects within a Gradle build script.
 * This setup ensures that the project is configured with the necessary Kotlin and Java options,
 * including support for Java 11 APIs.
 *
 * The configureKotlinJvm function is an internal extension function for the Project class in Gradle,
 * designed to configure Kotlin-specific options for JVM (non-Android) projects.
 * This function ensures that the project adheres to specific standards and uses
 * the appropriate versions of Java and Kotlin.
 *
 * Within the function, the `extensions.configure` method is used to configure the `JavaPluginExtension.`
 * This block sets the `sourceCompatibility` and `targetCompatibility` to Java 11,
 * ensuring that the project can use Java 11 APIs:
 *
 * `sourceCompatibility = JavaVersion.VERSION_11
 * targetCompatibility = JavaVersion.VERSION_11`
 *
 * After configuring the Java options, the function calls `configureKotlin`
 * with `KotlinJvmProjectExtension` to apply additional Kotlin-specific configurations.
 * This is done using a generic function that configures Kotlin options based on the project type:
 *
 *
 *  `configureKotlin<KotlinJvmProjectExtension>()`
 *
 *
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.toVersion(BuildLogicConstants.JAVA_VERSION)
        targetCompatibility = JavaVersion.toVersion(BuildLogicConstants.JAVA_VERSION)
    }

    // Configure Kotlin options via tasks
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(BuildLogicConstants.KOTLIN_JVM_TARGET))
            val warningsAsErrors: String? by project
            allWarningsAsErrors.set(warningsAsErrors.toBoolean())
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }
}

