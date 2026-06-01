plugins {
    alias(libs.plugins.ma3.android.feature)
}

android {
    namespace = "ke.don.ma3routes.features.routes.list"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
}