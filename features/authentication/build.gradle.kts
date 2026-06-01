plugins {
    alias(libs.plugins.ma3.android.feature)
}

android {
    namespace = "ke.don.ma3routes.features.authentication"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
}