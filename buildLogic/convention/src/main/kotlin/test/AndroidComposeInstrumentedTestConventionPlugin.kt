package test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.libs

/**
 * Convention plugin for Compose Instrumented Tests (Compose UI/E2E Tests)
 *
 * This plugin configures dependencies specifically for testing Jetpack Compose UI components
 * on a device or emulator. It extends instrumented testing with Compose-specific testing utilities.
 *
 * **Purpose:**
 * - Configure Compose UI testing dependencies
 * - Enable Compose test rules and assertions
 * - Set up semantics tree testing
 * - Configure test manifest for debugging
 *
 * **When to use:**
 * Apply this plugin to modules that contain Compose UI tests in the androidTest source set.
 * This is for testing @Composable functions, UI state, and user interactions with Compose.
 *
 * **Requires:**
 * - Must be used with a Compose-enabled module
 * - Usually combined with `dashiki.android.instrumented.test` for full test setup
 *
 * **Usage:**
 * ```kotlin
 * plugins {
 *     alias(libs.plugins.dashiki.android.instrumented.test)
 *     alias(libs.plugins.dashiki.android.compose.instrumented.test)
 * }
 * ```
 */
class AndroidComposeInstrumentedTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                // Compose UI Test - Testing framework for Compose UI
                add("androidTestImplementation", libs.findLibrary("androidxComposeUiTest").get())

                // Compose Test Manifest - Required for @Preview and debug builds
                add("debugImplementation", libs.findLibrary("androidxComposeUiTestManifest").get())
            }
        }
    }
}
