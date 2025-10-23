package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
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
