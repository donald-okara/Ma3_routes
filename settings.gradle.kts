pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ma3 routes"
include(":app")

// Features
include(":features:routes:list")
include(":features:routes:details")
include(":features:routes:data")
include(":features:stages:list")
include(":features:stages:navigation")
include(":features:stages:data")
include(":features:profile")
include(":features:preferences")
include(":features:authentication")

// Datasources
include(":datasources:remote")
include(":datasources:local")

// Core
include(":core:ui")
include(":core:domain")
include(":core:analytics")

include(":sync")
