package ke.don.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("ma3.android.library")
                apply("ma3.android.compose")
                apply("ma3.hilt")
            }

            dependencies {
                add("implementation", project(":core:analytics"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:resources"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":datasources:controller"))
            }
        }
    }
}
