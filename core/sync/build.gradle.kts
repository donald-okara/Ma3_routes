plugins {
    alias(libs.plugins.ma3.android.library)
}

android {
    namespace = "ke.don.ma3routes.core.sync"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:analytics"))

    // Features
    implementation(project(":features:authentication"))
    implementation(project(":features:preferences"))
    implementation(project(":features:profile"))
    implementation(project(":features:routes:details"))
    implementation(project(":features:routes:list"))
    implementation(project(":features:stages:list"))
    implementation(project(":features:stages:navigation"))
}
