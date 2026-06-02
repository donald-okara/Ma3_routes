plugins {
    alias(libs.plugins.ma3.android.feature)
}

android {
    namespace = "ke.don.ma3routes.features.routes.details"
}

dependencies {
    implementation(project(":features:routes:data"))
}
