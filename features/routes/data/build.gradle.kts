plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.features.routes.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":datasources:local"))
    implementation(project(":datasources:remote"))
}
