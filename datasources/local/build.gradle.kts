plugins {
    alias(libs.plugins.ma3.android.library)
    alias(libs.plugins.ma3.hilt.convention)
}

android {
    namespace = "ke.don.ma3routes.datasources.local"
}

dependencies {
    implementation(project(":core:resources"))
}
