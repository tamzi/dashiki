package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * JUnit 5 configuration utility
 *
 * **IMPORTANT: This is a UTILITY, not a convention plugin.**
 *
 * **What this does:**
 * - Configures Android projects to use JUnit 5 (Jupiter) for unit tests
 * - Enables JUnit Platform test runner
 * - Adds JUnit 5 dependencies (API, engine, parameters)
 * - Only affects src/test/ (unit tests), NOT src/androidTest/ (instrumented tests)
 *
 * **What this is NOT:**
 * - This is NOT a standalone testing solution
 * - This is NOT for instrumented tests (those still use JUnit 4)
 * - This is NOT a plugin that modules apply directly
 *
 * **Why JUnit 5 vs JUnit 4:**
 * ```
 * JUnit 4 (Old):                    JUnit 5 (New):
 * @org.junit.Test                   @org.junit.jupiter.api.Test
 * @org.junit.Before                 @org.junit.jupiter.api.BeforeEach
 * @org.junit.BeforeClass            @org.junit.jupiter.api.BeforeAll
 * Limited assertions                Rich assertions & assumptions
 * No parameterized tests            Built-in @ParameterizedTest
 * No test templates                 @TestTemplate, @RepeatedTest, etc.
 * ```
 *
 * **Relationship to testing conventions:**
 * ```
 * JUnit5.kt (THIS FILE):
 *   └─> Utility function for JUnit 5 setup
 *   └─> Called BY AndroidApplicationConventionPlugin
 *   └─> Called BY AndroidLibraryConventionPlugin
 *   └─> Runs automatically for all modules
 *
 * AndroidUnitTestConventionPlugin:
 *   └─> Opt-in convention for unit testing
 *   └─> Adds Coroutines Test, Truth, Turbine, Robolectric
 *   └─> Works WITH JUnit 5 (not instead of)
 * ```
 *
 * **Usage:**
 * Called from base conventions (application/library):
 * ```kotlin
 * class AndroidApplicationConventionPlugin : Plugin<Project> {
 *     override fun apply(target: Project) {
 *         with(target) {
 *             extensions.configure<ApplicationExtension> {
 *                 configureKotlinAndroid(this)
 *                 configureJUnit5(this) // <-- This utility
 *                 // ...
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * **When tests run:**
 * ```
 * ./gradlew test
 *   └─> Uses JUnit Platform (JUnit 5)
 *   └─> Runs tests in src/test/
 *   └─> Modern JUnit 5 features available
 *
 * ./gradlew connectedAndroidTest
 *   └─> Uses JUnit 4 (required by Android)
 *   └─> Runs tests in src/androidTest/
 *   └─> JUnit 5 NOT available here
 * ```
 *
 * **Dependencies provided:**
 * - `junit-jupiter-api` - JUnit 5 API for writing tests
 * - `junit-jupiter-engine` - Engine to run tests
 * - `junit-jupiter-params` - Parameterized test support
 *
 * **Controlled by:**
 * See `DependencyConfig.JUnit.USE_JUNIT5_FOR_UNIT_TESTS` to enable/disable
 *
 * Configure JUnit 5 for Android unit tests
 *
 * This function configures the Android test options to use JUnit Platform (JUnit 5)
 * for running unit tests. This allows using JUnit 5 features like @Test from
 * org.junit.jupiter.api instead of the older JUnit 4 annotations.
 *
 * Note: This only affects unit tests (in src/test/). Android instrumented tests
 * (in src/androidTest/) will continue to use JUnit 4 as required by Android.
 */
internal fun Project.configureJUnit5(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    // Only configure if enabled in DependencyConfig

    commonExtension.apply {
        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }
    }

    // Add JUnit 5 dependencies
    dependencies {
        add("testImplementation", libs.findLibrary("junit5").get())
        add("testRuntimeOnly", libs.findLibrary("junit5.engine").get())
        add("testImplementation", libs.findLibrary("junit5.params").get())
    }
}
