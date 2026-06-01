plugins {
    alias(libs.plugins.ma3.android.feature)
}

android {
    namespace = "ke.don.ma3routes.features.stages.list"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
}