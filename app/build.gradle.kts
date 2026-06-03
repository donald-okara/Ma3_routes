plugins {
    alias(libs.plugins.ma3.android.application)
}

dependencies {
    // Features
    implementation(project(":features:authentication"))
    implementation(project(":features:preferences"))
    implementation(project(":features:profile"))
    implementation(project(":features:routes:details"))
    implementation(project(":features:routes:list"))
    implementation(project(":features:stages:list"))
    implementation(project(":features:stages:navigation"))

    // Core
    implementation(project(":core:analytics"))
    implementation(project(":core:domain"))
    implementation(project(":datasources:sync"))
    implementation(project(":core:ui"))
}
