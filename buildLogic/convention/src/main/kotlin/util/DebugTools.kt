package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure debug tools (logging and memory leak detection) for the application.
 *
 * This function sets up comprehensive debugging tools including Timber for logging
 * and LeakCanary for automatic memory leak detection.
 *
 * **Tools Included:**
 * - **Timber** - Structured logging library for better log management
 * - **LeakCanary** - Automatic memory leak detection (debug builds only)
 * - **BuildConfig fields** - DEBUG_MODE and LOG_LEVEL for runtime checks
 *
 * **What is LeakCanary?**
 * LeakCanary automatically detects memory leaks in your app. When a leak is detected:
 * 1. Shows a notification with leak details
 * 2. Creates a heap dump for analysis
 * 3. Provides a detailed trace of what's leaking
 * 4. Zero configuration needed - works automatically!
 *
 * **Important:** LeakCanary is only included in debug builds (via `debugImplementation`).
 * Release builds are not affected.
 *
 * **Usage in convention plugin:**
 * ```kotlin
 * extensions.configure<ApplicationExtension> {
 *     configureDebugTools(this)
 * }
 * ```
 *
 * **In your Application class:**
 * ```kotlin
 * class MyApp : Application() {
 *     override fun onCreate() {
 *         super.onCreate()
 *
 *         if (BuildConfig.DEBUG_MODE) {
 *             Timber.plant(Timber.DebugTree())
 *         }
 *         // LeakCanary is automatically initialized - no setup needed!
 *     }
 * }
 * ```
 *
 * **BuildConfig fields created:**
 * - `BuildConfig.DEBUG_MODE` - true in debug, false in release
 * - `BuildConfig.LOG_LEVEL` - "DEBUG" in debug, "ERROR" in release
 */
fun Project.configureDebugTools(commonExtension: CommonExtension<*, *, *, *, *, *>) {
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

    // Add debug tools dependencies
    dependencies {
        add("implementation", libs.findLibrary("timber").get())

        // LeakCanary - Memory leak detection in debug builds
        add("debugImplementation", libs.findLibrary("leakcanary").get())
    }
}

/**
 * Configure debug tools specifically for library modules.
 *
 * Library modules typically don't need full debug infrastructure,
 * but might benefit from debug mode detection.
 */
fun Project.configureLibraryDebugTools(commonExtension: CommonExtension<*, *, *, *, *, *>) {
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
