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
package ke.don.ma3routes.features.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import ke.don.ma3routes.core.domain.model.ThemeConfig
import ke.don.ma3routes.core.domain.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserDataRepository {

    private object PreferencesKeys {
        val THEME_CONFIG = stringPreferencesKey("theme_config")
    }

    override val themeConfig: Flow<ThemeConfig> = dataStore.data.map { preferences ->
        val configName = preferences[PreferencesKeys.THEME_CONFIG] ?: ThemeConfig.DYNAMIC.name
        try {
            ThemeConfig.valueOf(configName)
        } catch (e: IllegalArgumentException) {
            ThemeConfig.DYNAMIC
        }
    }

    override suspend fun setThemeConfig(themeConfig: ThemeConfig) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME_CONFIG] = themeConfig.name
        }
    }
}
