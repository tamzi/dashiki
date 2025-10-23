package util

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

/**
 * Product flavor dimensions for the Dashiki app.
 *
 * Flavors allow you to create different versions of your app from a single codebase.
 * Common use cases:
 * - Free vs. Paid versions
 * - Demo vs. Production data sources
 * - Different API endpoints (staging, production)
 */
enum class FlavorDimension {
    /**
     * Content source dimension - controls where the app gets its data from
     */
    contentType
}

/**
 * Supported product flavors in the Dashiki app.
 *
 * Each flavor represents a different variant of the app that can be built.
 */
enum class DashikiFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {
    /**
     * Demo flavor - uses local/mock data, no backend required.
     * Perfect for:
     * - Development without backend
     * - UI testing
     * - Demo presentations
     * - App store screenshots
     */
    DEMO(
        dimension = FlavorDimension.contentType,
        applicationIdSuffix = ".demo",
        versionNameSuffix = "-demo"
    ),

    /**
     * Production flavor - uses real backend/data sources.
     * This is the version that goes to production.
     */
    PROD(
        dimension = FlavorDimension.contentType
    )
}

/**
 * Configure product flavors for the application.
 *
 * This function sets up the flavor dimensions and creates all defined flavors
 * with their respective configurations.
 *
 * Usage in convention plugin:
 * ```kotlin
 * extensions.configure<ApplicationExtension> {
 *     configureFlavors(this)
 * }
 * ```
 */
fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: DashikiFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.contentType.name

        productFlavors {
            DashikiFlavor.values().forEach { flavor ->
                create(flavor.name.lowercase()) {
                    dimension = flavor.dimension.name

                    // Apply flavor-specific configuration
                    flavorConfigurationBlock(this, flavor)

                    // Apply common flavor settings
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        flavor.applicationIdSuffix?.let {
                            applicationIdSuffix = it
                        }
                        flavor.versionNameSuffix?.let {
                            versionNameSuffix = it
                        }
                    }
                }
            }
        }
    }
}
