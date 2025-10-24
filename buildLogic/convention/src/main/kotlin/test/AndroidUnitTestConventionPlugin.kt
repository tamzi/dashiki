package test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.libs

/**
 * Convention plugin for Android Unit Tests (JVM Tests)
 *
 * This plugin configures dependencies for unit tests that run on the local JVM (not on a device).
 * These are fast, isolated tests for business logic, ViewModels, use cases, and pure functions.
 *
 * **Purpose:**
 * - Configure dependencies for JVM-based unit testing
 * - Set up coroutines testing utilities
 * - Enable Truth assertions
 * - Configure Turbine for Flow testing
 * - Set up Robolectric for Android framework testing on JVM
 *
 * **Test Types:**
 * - Business logic tests
 * - ViewModel tests
 * - Use case/repository tests
 * - Pure function tests
 * - Coroutine/Flow tests
 *
 * **When to use:**
 * Apply this plugin to modules that contain unit tests (test source set)
 * that need to test logic without requiring a device or emulator.
 *
 * **Usage:**
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.dashiki.android.unit.test)
 * }
 * ```
 */
class AndroidUnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Kotlin Coroutines Test - Testing utilities for coroutines and flows
                add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())

                // Truth - Fluent assertions library
                add("testImplementation", libs.findLibrary("truth").get())

                // Turbine - Flow testing library
                add("testImplementation", libs.findLibrary("turbine").get())

                // Robolectric - Android framework testing on JVM (optional but commonly used)
                add("testImplementation", libs.findLibrary("robolectric").get())
            }
        }
    }
}
