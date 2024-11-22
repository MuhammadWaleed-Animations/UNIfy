package com.mwafaimk.unify.core.util.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

object AuthPreferencesKeys {
    val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
}

class AuthStateManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    // Save login state to DataStore
    fun setLoginState(isLoggedIn: Boolean) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences[AuthPreferencesKeys.IS_LOGGED_IN_KEY] = isLoggedIn
            }
        }
    }

    // Retrieve login state from DataStore
    fun isLoggedIn(): Boolean {
        return runBlocking {
            dataStore.data.first()[AuthPreferencesKeys.IS_LOGGED_IN_KEY] ?: false
        }
    }

    // Observe login state from DataStore
    fun isLoggedInFlow(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences[AuthPreferencesKeys.IS_LOGGED_IN_KEY] ?: false
            }
    }

    // Clear login state from DataStore
    fun clearLoginState() {
        runBlocking {
            dataStore.edit { preferences ->
                preferences.remove(AuthPreferencesKeys.IS_LOGGED_IN_KEY)
            }
        }
    }
}
