plugins {
    alias(libs.plugins.ma3.android.library)
    alias(libs.plugins.ma3.hilt.convention)
}

android {
    namespace = "ke.don.ma3routes.datasources.sync"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:analytics"))
    implementation(project(":core:resources"))
    implementation(project(":datasources:controller"))
}
