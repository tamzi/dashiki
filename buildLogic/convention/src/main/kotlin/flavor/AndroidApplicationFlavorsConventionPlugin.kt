package flavor

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.configureFlavors

/**
 * Convention plugin for configuring product flavors in Android applications.
 *
 * This plugin applies the standard Dashiki product flavors (demo/prod) to an application module.
 *
 * Product flavors allow you to create different versions of your app:
 * - **demo**: Uses local/mock data, no backend required. Perfect for development and testing.
 * - **prod**: Uses real backend services. This is the production version.
 *
 * Build variants created:
 * - demoDebug
 * - demoRelease
 * - prodDebug
 * - prodRelease
 *
 * Apply this plugin to your app module:
 * ```kotlin
 * plugins {
 *     id("dashiki.android.application.flavors")
 * }
 * ```
 *
 * You can then build specific variants:
 * ```bash
 * ./gradlew assembleDemoDebug    # Demo version for testing
 * ./gradlew assembleProdRelease  # Production release
 * ```
 */
class AndroidApplicationFlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureFlavors(this)
            }
        }
    }
}
