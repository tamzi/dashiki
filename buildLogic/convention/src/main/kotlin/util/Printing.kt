package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure debug logging for the application.
 *
 * This function sets up Timber logging library and configures build config fields
 * to control logging behavior in different build types.
 *
 * Features:
 * - Adds Timber dependency for structured logging
 * - Creates DEBUG_MODE build config field
 * - Creates LOG_LEVEL build config field for granular control
 * - Configures ProGuard rules to keep logging in release builds (optional)
 *
 * Usage in convention plugin:
 * ```kotlin
 * extensions.configure<ApplicationExtension> {
 *     configurePrinting(this)
 * }
 * ```
 *
 * In your Application class:
 * ```kotlin
 * if (BuildConfig.DEBUG_MODE) {
 *     Timber.plant(Timber.DebugTree())
 * }
 * ```
 */
fun Project.configurePrinting(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        // Enable BuildConfig generation
        buildFeatures {
            buildConfig = true
        }

        // Configure build types with logging fields
        buildTypes {
            getByName("debug") {
                // Add build config fields for debug builds
                buildConfigField("boolean", "DEBUG_MODE", "true")
                buildConfigField("String", "LOG_LEVEL", "\"DEBUG\"")
            }

            getByName("release") {
                // Add build config fields for release builds
                buildConfigField("boolean", "DEBUG_MODE", "false")
                buildConfigField("String", "LOG_LEVEL", "\"ERROR\"")
            }
        }
    }

    // Add Timber logging library
    dependencies {
        add("implementation", libs.findLibrary("timber").get())

        // Optional: Add LeakCanary for memory leak detection in debug builds
        // add("debugImplementation", libs.findLibrary("leakcanary").get())
    }
}

/**
 * Configure printing/logging specifically for library modules.
 *
 * Library modules typically don't need full logging infrastructure,
 * but might benefit from debug logging capabilities.
 */
fun Project.configureLibraryPrinting(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            getByName("debug") {
                buildConfigField("boolean", "DEBUG_MODE", "true")
            }

            getByName("release") {
                buildConfigField("boolean", "DEBUG_MODE", "false")
            }
        }
    }
}
