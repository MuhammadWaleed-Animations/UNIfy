package com.mwafaimk.unify.core.util.datastore

import android.util.Base64
import android.util.Log
import com.mwafaimk.unify.data.model.user.User
import com.mwafaimk.unify.data.model.user.login.LoginResponse


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class DataManager @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    // Flow for User data
    val userFlow: Flow<LoginResponse?> = dataStoreManager.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_DATA_KEY]?.let { jsonString ->
            Json.decodeFromString<LoginResponse>(jsonString)
        }
    }

    // Save User data
    fun saveUser(user: LoginResponse) {
        val jsonString = Json.encodeToString(user)
        dataStoreManager.saveData(DataStoreKeys.USER_DATA_KEY, jsonString)
    }

    // Delete User data
    fun deleteUser() {
        dataStoreManager.deleteData(DataStoreKeys.USER_DATA_KEY)
    }
}


//class DataManager @Inject constructor(
//    private val dataStoreManager: DataStoreManager
//)
//{
//    // Flow for User data
//    val userFlow: Flow<LoginResponse?> = dataStoreManager.dataStore.data.map { preferences ->
//        preferences[DataStoreKeys.USER_DATA_KEY]?.let { jsonString ->
//            Json.decodeFromString<LoginResponse>(jsonString)
//        }
//    }
//
//
//    // Save User data
//    fun saveUser(user: LoginResponse) {
//        val jsonString = Json.encodeToString(LoginResponse)
//        dataStoreManager.saveData(DataStoreKeys.USER_DATA_KEY, jsonString)
//    }
//    fun deleteUser() {
//        dataStoreManager.deleteData(DataStoreKeys.USER_DATA_KEY)
//    }
//
//}