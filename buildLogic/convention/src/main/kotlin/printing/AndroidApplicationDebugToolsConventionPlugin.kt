package printing

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.configureDebugTools

/**
 * Convention plugin for configuring debug tools in Android applications.
 *
 * This plugin sets up comprehensive debugging tools including:
 * - **Timber** - Structured logging
 * - **LeakCanary** - Memory leak detection (debug builds only)
 * - **BuildConfig fields** - DEBUG_MODE and LOG_LEVEL
 *
 * **Why use this plugin?**
 * - Zero-config memory leak detection with LeakCanary
 * - Professional logging with Timber
 * - Debug/Release aware configuration
 *
 * **Apply this plugin:**
 * ```kotlin
 * plugins {
 *     id("dashiki.android.application.debugtools")
 * }
 * ```
 *
 * **Usage in your Application class:**
 * ```kotlin
 * class DashikiApplication : Application() {
 *     override fun onCreate() {
 *         super.onCreate()
 *
 *         if (BuildConfig.DEBUG_MODE) {
 *             Timber.plant(Timber.DebugTree())
 *         }
 *         // LeakCanary auto-initializes - nothing to do!
 *     }
 * }
 * ```
 *
 * **Usage in your code:**
 * ```kotlin
 * Timber.d("Debug message")
 * Timber.e(throwable, "Error message")
 * Timber.i("Info message")
 * Timber.w("Warning message")
 * ```
 *
 * **LeakCanary:**
 * - Automatically detects memory leaks
 * - Shows notifications when leaks detected
 * - Only included in debug builds
 * - Zero configuration required
 */
class AndroidApplicationDebugToolsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureDebugTools(this)
            }
        }
    }
}
