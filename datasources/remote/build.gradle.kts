/*
 * Copyright 2025 Donald Isoe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Properties

plugins {
    alias(libs.plugins.ma3.android.library)
    alias(libs.plugins.ma3.hilt.convention)
}

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}

android {
    namespace = "ke.don.ma3routes.datasources.remote"

    defaultConfig {
        val baseUrl = localProperties.getProperty("base_url") ?: ""
        val apiKey = localProperties.getProperty("api_key") ?: ""
        val webClientId = localProperties.getProperty("web_client_id") ?: ""

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "WEB_CLIENT_ID", "\"$webClientId\"")
    }
}

dependencies {
    implementation(project(":core:resources"))
    implementation(project(":core:domain"))

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)

    //noinspection LoginCredentials
    implementation(libs.androidx.credentials)
    //noinspection LoginCredentials
    implementation (libs.googleid)
    //noinspection LoginCredentials
    implementation(libs.androidx.credentials.play.services.auth)

    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.kotlinx.coroutines.test)
}
