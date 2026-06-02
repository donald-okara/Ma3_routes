plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.sync"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:analytics"))
    implementation(project(":datasources:controller"))
}
