package util

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

/**
 * Utility for disabling unnecessary Android instrumented tests
 *
 * **IMPORTANT: This is a UTILITY, not a convention plugin.**
 *
 * **What this does:**
 * - Disables androidTest compilation for library modules that don't have src/androidTest folder
 * - Prevents waste of build time compiling, packaging, and installing empty test APKs
 * - Should be called from library convention plugins
 *
 * **What this is NOT:**
 * - This is NOT a dependency provider (see AndroidInstrumentedTestConventionPlugin for that)
 * - This is NOT a plugin that modules apply directly
 * - This does NOT add testing dependencies
 *
 * **Relationship to AndroidInstrumentedTestConventionPlugin:**
 * ```
 * AndroidInstrumentedTests.kt (THIS FILE):
 *   └─> Utility function to optimize builds
 *   └─> Called BY convention plugins
 *   └─> Disables empty test builds
 *
 * AndroidInstrumentedTestConventionPlugin.kt:
 *   └─> Convention plugin applied BY modules
 *   └─> Provides testing dependencies
 *   └─> Adds androidx.test, Espresso, etc.
 * ```
 *
 * **Usage:**
 * Called from library conventions like this:
 * ```kotlin
 * class AndroidLibraryConventionPlugin : Plugin<Project> {
 *     override fun apply(target: Project) {
 *         with(target) {
 *             // ... other configuration ...
 *
 *             // Use this utility to optimize builds
 *             extensions.configure<LibraryAndroidComponentsExtension> {
 *                 disableUnnecessaryAndroidTests(project)
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * **Why separate these concerns:**
 * - Build optimization (this file) vs dependency management (convention plugin)
 * - This runs for ALL library modules automatically
 * - Test dependencies are opt-in via the convention plugin
 *
 * **Example scenario:**
 * - Library module `core:utils` has no src/androidTest folder
 * - This utility detects that and skips androidTest build
 * - Saves ~5-10 seconds per clean build
 * - If later you add tests, it automatically re-enables
 *
 * Disable unnecessary Android instrumented tests for the [project] if there is no `androidTest` folder.
 * Otherwise, these projects would be compiled, packaged, installed and ran only
 * to end-up with the following message:
 *
 * > Starting 0 tests on AVD
 *
 * Note: this could be improved by checking other potential sourceSets based on buildTypes and flavors.
 */
internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants {
    it.enableAndroidTest = it.enableAndroidTest
        && project.projectDir.resolve("src/androidTest").exists()
}
