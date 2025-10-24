package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configure Compose-specific options
 *
 * This utility configures Jetpack Compose for Android modules including:
 * - Compose BOM and core dependencies
 * - UI tooling for previews and debugging
 * - Material Design 3 components
 * - Extended Material icons
 * - ConstraintLayout for Compose
 * - Compose compiler settings
 *
 * **Dependencies Provided:**
 * All dependencies are automatically included when using Compose conventions:
 * - `androidx.compose:compose-bom` - Version management
 * - `androidx.activity:activity-compose` - Activity integration
 * - `androidx.compose.material3:material3` - Material 3 components
 * - `androidx.compose.ui:ui-tooling-preview` - Preview support
 * - `androidx.compose.ui:ui-tooling` (debug) - Debug tools
 * - `androidx.compose.material:material-icons-extended` - Icon library
 * - `androidx.constraintlayout:constraintlayout-compose` - Layout support
 *
 * **Compiler Configuration:**
 * Compiler settings can be controlled via `DependencyConfig.Compose`:
 * - `ENABLE_STRONG_SKIPPING` - Enable/disable strong skipping mode
 * - `STABILITY_CONFIG_FILE` - Path to stability configuration
 *
 * **Usage:**
 * Called automatically by Compose convention plugins - not for direct use.
 */
internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            // Compose BOM for version management
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            // Core Compose dependencies - always included
            add("implementation", libs.findLibrary("androidx-activity-compose").get())
            add("implementation", libs.findLibrary("androidx-compose-material3").get())

            // UI Tooling for previews and debugging
            add("implementation", libs.findLibrary("androidxComposeUiToolingPreview").get())
            add("debugImplementation", libs.findLibrary("androidxComposeUiTooling").get())

            // Material Icons Extended - commonly used across modules
            add("implementation", libs.findLibrary("androidx-compose-material-iconsExtended").get())

            // ConstraintLayout Compose - for complex layouts
            add("implementation", libs.findLibrary("androidx-constraintlayout-compose").get())
        }

        testOptions {
            unitTests {
                // For Robolectric compatibility
                isIncludeAndroidResources = true
            }
        }
    }

    // Configure Compose compiler
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }

        fun Provider<*>.relativeToRootProject(dir: String) =
            flatMap {
                rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
            }.map { it.dir(dir) }

        // Compiler metrics (enabled via gradle property: enableComposeCompilerMetrics=true)
        project.providers
            .gradleProperty("enableComposeCompilerMetrics")
            .onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        // Compiler reports (enabled via gradle property: enableComposeCompilerReports=true)
        project.providers
            .gradleProperty("enableComposeCompilerReports")
            .onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)

        // Stability configuration - controlled by DependencyConfig
        stabilityConfigurationFiles.set(
            listOf(rootProject.layout.projectDirectory.file(DependencyConfig.Compose.STABILITY_CONFIG_FILE))
        )

    }
}
