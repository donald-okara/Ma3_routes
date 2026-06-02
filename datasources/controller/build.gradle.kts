plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.datasources.controller"
}

dependencies {
    implementation(project(":datasources:local"))
    implementation(project(":datasources:remote"))
    implementation(project(":core:domain"))
}
