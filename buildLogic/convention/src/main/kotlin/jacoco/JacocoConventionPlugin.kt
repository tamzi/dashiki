package jacoco

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import kotlin.text.toBigDecimal

/**
 * Convention plugin for JaCoCo code coverage configuration
 *
 * This plugin provides standardized JaCoCo configuration for all modules
 * that need code coverage reporting. It includes:
 * - JaCoCo plugin application
 * - Standard configuration
 * - Coverage verification rules
 * - Report generation tasks
 */
class JacocoConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            // Apply JaCoCo plugin
            pluginManager.apply("jacoco")

            // Configure JaCoCo extension
            configure<JacocoPluginExtension> {
                toolVersion = "0.8.7"
            }

            // Configure test tasks to generate coverage
            tasks.withType<Test> {
                finalizedBy("jacocoTestReport")
            }

            // Configure JaCoCo report task
            tasks.withType<JacocoReport> {
                dependsOn("testDebugUnitTest")

                reports {
                    xml.required.set(true)
                    html.required.set(true)
                }

                // Exclude common files from coverage
                val fileFilter = listOf(
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/Manifest*.*",
                    "**/*Test*.*",
                    "**/*Test$*.*",
                    "android/**/*.*",
                    "**/di/**",
                    "**/hilt/**",
                    "**/dagger/**",
                    "**/*_Factory.*",
                    "**/*_MembersInjector.*",
                    "**/*_HiltModules*.*",
                    "**/Hilt_*.*",
                    "**/*_GeneratedInjector.*",
                    "**/*_GeneratedAccessor.*",
                    "**/*_GeneratedEntryPoint.*"
                )

                val debugTree = fileTree(layout.buildDirectory.get().asFile) {
                    include("**/classes/**/main/**")
                    exclude(fileFilter)
                }

                val mainSrc = files("${project.projectDir}/src/main/java")

                classDirectories.setFrom(debugTree)
                sourceDirectories.setFrom(mainSrc)

                executionData.setFrom(fileTree(layout.buildDirectory.get().asFile) {
                    include("**/jacoco/*.exec")
                })
            }

            // Configure coverage verification task
            tasks.withType<JacocoCoverageVerification> {
                dependsOn("jacocoTestReport")

                violationRules {
                    rule {
                        limit {
                            minimum = "0.70".toBigDecimal() // 70% minimum coverage
                        }
                    }

                    rule {
                        element = "CLASS"
                        excludes = listOf(
                            "*.BuildConfig",
                            "*.R",
                            "*.R$*",
                            "*.*Test*",
                            "*.*Test$*",
                            "*.*Factory*",
                            "*.*MembersInjector*",
                            "*.*HiltModules*",
                            "*.*Hilt_*",
                            "*.*GeneratedInjector*",
                            "*.*GeneratedAccessor*",
                            "*.*GeneratedEntryPoint*"
                        )

                        limit {
                            counter = "LINE"
                            value = "COVEREDRATIO"
                            minimum = "0.60".toBigDecimal() // 60% line coverage
                        }
                    }
                }

                val fileFilter = listOf(
                    "**/R.class",
                    "**/R$*.class",
                    "**/BuildConfig.*",
                    "**/Manifest*.*",
                    "**/*Test*.*",
                    "**/*Test$*.*",
                    "android/**/*.*",
                    "**/di/**",
                    "**/hilt/**",
                    "**/dagger/**",
                    "**/*_Factory.*",
                    "**/*_MembersInjector.*",
                    "**/*_HiltModules*.*",
                    "**/Hilt_*.*",
                    "**/*_GeneratedInjector.*",
                    "**/*_GeneratedAccessor.*",
                    "**/*_GeneratedEntryPoint.*"
                )

                val debugTree = fileTree(layout.buildDirectory.get().asFile) {
                    include("**/classes/**/main/**")
                    exclude(fileFilter)
                }

                classDirectories.setFrom(debugTree)
                executionData.setFrom(fileTree(layout.buildDirectory.get().asFile) {
                    include("**/jacoco/*.exec")
                })
            }
        }
    }
}
