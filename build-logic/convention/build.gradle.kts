/*
 * Copyright 2025 Donald Isoe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
plugins {
    `kotlin-dsl`
}

group = "ke.don.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.hilt.android.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = libs.plugins.ma3.android.library.get().pluginId
            implementationClass = "ke.don.convention.AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.ma3.android.application.get().pluginId
            implementationClass = "ke.don.convention.AndroidApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.ma3.android.feature.get().pluginId
            implementationClass = "ke.don.convention.AndroidFeatureConventionPlugin"
        }
        register("androidCompose") {
            id = libs.plugins.ma3.android.compose.get().pluginId
            implementationClass = "ke.don.convention.AndroidComposeConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.ma3.hilt.convention.get().pluginId
            implementationClass = "ke.don.convention.HiltConventionPlugin"
        }
    }
}
