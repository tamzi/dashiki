package application

import com.android.build.api.dsl.ApplicationExtension
import util.configureKotlinAndroid
import util.configureJUnit5
import util.BuildLogicConstants
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *
 * This plugin sets up an Android application project with the necessary plugins and configurations,
 * including Kotlin support, dependency management, and specific Android settings.
 *
 *
 * The `AndroidApplicationConventionPlugin` class implements the `Plugin<Project>` interface in Gradle,
 * which allows it to be applied to a Gradle project.
 *
 * This plugin is designed to configure Android application projects with specific settings and plugins.
 *
 * The `apply` method is overridden to define the plugin's behavior when it is applied to a project.
 * Inside this method, the `with(target)` block is used to operate on the `Project` instance
 * passed as the `target` parameter.
 *
 *  `class AndroidApplicationConventionPlugin : Plugin<Project> {
 *     override fun apply(target: Project) {
 *         with(target) {
 *             // Configuration code
 *         }
 *     }
 *  }`
 *
 *  Within the `with(target)` block, the `pluginManager` is used to apply several plugins to the project.
 *  These plugins include `com.android.application` for Android application support,
 *  `org.jetbrains.kotlin.android` for Kotlin support, and
 *  `com.dropbox.dependency-guard` for dependency management.
 *  `dashiki.android.lint` for adding a missing plugin for linting.
 *
 *   `with(pluginManager) {
 *     apply("com.android.application")
 *     apply("org.jetbrains.kotlin.android")
 *     apply("com.dropbox.dependency-guard")
 *     apply("dashiki.android.lint")
 *   }`
 *
 *  Next, the `extensions.configure<ApplicationExtension>` method is used to
 *  configure the `ApplicationExtension` instance. This extension provides access to
 *  Android-specific configuration options. The `configureKotlinAndroid` function is called
 *  to apply Kotlin-specific configurations to the Android project.
 *
 *   `extensions.configure<ApplicationExtension> {
 *     configureKotlinAndroid(this)
 *     defaultConfig.targetSdk = 34
 *     @Suppress("UnstableApiUsage")
 *     testOptions.animationsDisabled = true
 *  }`
 *
 *  The `defaultConfig.targetSdk` property is set to `34`, which specifies the target SDK version for the project.
 *
 *  The `@Suppress("UnstableApiUsage")` annotation is used to suppress warnings about unstable API usage.
 *
 *  The `testOptions.animationsDisabled` property is set to `true` to disable animations during testing.
 *
 *  This plugin is designed to configure Android application projects with specific settings and plugins.
 *
 *  Within the `ApplicationExtension` configuration block,
 *  the targetSdk version is set to 34, and animations are disabled in test options to speed up tests.
 *
 *
 *
 * */

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("dashiki.android.lint")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureJUnit5(this) // Add JUnit 5 configuration
                defaultConfig.targetSdk = BuildLogicConstants.TARGET_SDK
                defaultConfig.minSdk = BuildLogicConstants.MIN_SDK
                @Suppress("UnstableApiUsage")
                testOptions.animationsDisabled = true
            }
        }
    }
}
