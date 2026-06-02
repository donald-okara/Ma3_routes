plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.sync"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:analytics"))

    // Features
    implementation(project(":features:authentication"))
    implementation(project(":features:preferences"))
    implementation(project(":features:profile"))
    
    // Feature Data Modules
    implementation(project(":features:routes:data"))
    implementation(project(":features:stages:data"))
}
