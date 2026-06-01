plugins {
    `kotlin-dsl`
}

group = "ke.don.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
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
    }
}