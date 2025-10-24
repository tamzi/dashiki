package test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.libs

/**
 * Convention plugin for Android Instrumented Tests (UI/E2E Tests)
 *
 * This plugin configures dependencies for Android instrumented tests that run on a device or emulator.
 * These are integration/E2E tests that verify the UI behavior and interactions.
 *
 * **Purpose:**
 * - Configure dependencies for UI testing with Compose
 * - Set up AndroidX Test framework
 * - Enable Espresso for UI interactions
 * - Configure JUnit for test execution
 *
 * **When to use:**
 * Apply this plugin to modules that contain instrumented tests (androidTest source set)
 * that need to test UI components, user interactions, or integration scenarios.
 *
 * **Usage:**
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.dashiki.android.instrumented.test)
 * }
 * ```
 */
class AndroidInstrumentedTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // AndroidX Test - Core testing framework
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-core").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-rules").get())

                // Espresso - UI testing framework
                add(
                    "androidTestImplementation",
                    libs.findLibrary("androidx-test-espresso-core").get()
                )
            }
        }
    }
}
