package printing

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.configurePrinting

/**
 * Convention plugin for configuring debug logging in Android applications.
 *
 * This plugin sets up Timber logging and build config fields for controlling
 * logging behavior across different build types.
 *
 * Features provided:
 * - Timber logging library for structured logging
 * - BuildConfig.DEBUG_MODE field (true in debug, false in release)
 * - BuildConfig.LOG_LEVEL field ("DEBUG" in debug, "ERROR" in release)
 *
 * Apply this plugin to modules that need logging:
 * ```kotlin
 * plugins {
 *     id("dashiki.android.application.printing")
 * }
 * ```
 *
 * Usage in your Application class:
 * ```kotlin
 * class DashikiApplication : Application() {
 *     override fun onCreate() {
 *         super.onCreate()
 *
 *         if (BuildConfig.DEBUG_MODE) {
 *             Timber.plant(Timber.DebugTree())
 *         }
 *     }
 * }
 * ```
 *
 * Usage in your code:
 * ```kotlin
 * Timber.d("Debug message")
 * Timber.e("Error message")
 * Timber.i("Info message")
 * ```
 */
class AndroidApplicationPrintingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configurePrinting(this)
            }
        }
    }
}
