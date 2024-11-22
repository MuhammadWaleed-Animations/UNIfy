package com.mwafaimk.unify.core.util.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    val dataStore: DataStore<Preferences>
) {

    // Save data to DataStore
    fun saveData(key: Preferences.Key<String>, value: String) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    // Retrieve data from DataStore
    fun getData(key: Preferences.Key<String>): String? {
        return runBlocking {
            dataStore.data.first()[key]
        }
    }

    // Delete data from DataStore
    fun deleteData(key: Preferences.Key<String>) {
        runBlocking {
            dataStore.edit { preferences ->
                preferences.remove(key)
            }
        }
    }
}