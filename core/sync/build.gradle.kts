plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.core.sync"
}

dependencies {
    implementation(project(":core:domain"))
}