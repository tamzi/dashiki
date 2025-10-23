package feature

import com.android.build.gradle.LibraryExtension
import util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Convention plugin for feature modules in the Dashiki project.
 *
 * Feature modules are UI-focused modules that implement user-facing functionality.
 * They typically depend on core modules for shared logic and data access.
 *
 * This plugin applies:
 * - Android library configuration
 * - Hilt for dependency injection
 * - Common feature module dependencies
 *
 * To use this plugin, feature modules should be structured under a :feature directory
 * and depend on appropriate :core modules as needed.
 */
class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("dashiki.android.library")
                apply("dashiki.android.library.compose")
                apply("dashiki.hilt")
            }

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                // TODO: Add core module dependencies when core modules are created
                 add("implementation", project(":dds"))
                // add("implementation", project(":core:domain"))

                // Common feature dependencies
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())

                add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
            }
        }
    }
}
