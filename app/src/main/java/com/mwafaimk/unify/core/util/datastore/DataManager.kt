package com.mwafaimk.unify.core.util.datastore

import android.util.Base64
import android.util.Log

import com.mwafaimk.unify.data.model.user.LoginUser
import com.mwafaimk.unify.data.model.user.User


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
)
{

    private val DEFAULT_LANGUAGE = "en"

    // Define constants for AES encryption
    companion object {
        private const val AES_MODE = "AES/GCM/NoPadding"
        private const val KEY_SIZE = 256
        private const val GCM_TAG_LENGTH = 128
        private const val IV_SIZE = 12 // 12 bytes for GCM recommended by NIST
    }

    // Generate or retrieve the AES secret key (use a secure source in production)
    private val secretKey: SecretKey = KeyGenerator.getInstance("AES").apply {
        init(KEY_SIZE)
    }.generateKey()

    // Encryption function using AES-GCM
    private fun encrypt(data: String): String {
        val cipher = Cipher.getInstance(AES_MODE)
        val iv = ByteArray(IV_SIZE).also { SecureRandom().nextBytes(it) } // Generate a new IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(GCM_TAG_LENGTH, iv))
        val encryptedData = cipher.doFinal(data.toByteArray())
        // Combine IV and encrypted data for storage
        val combined = iv + encryptedData
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    // Decryption function using AES-GCM
    private fun decrypt(data: String): String {
        val combined = Base64.decode(data, Base64.DEFAULT)
        val iv = combined.sliceArray(0 until IV_SIZE) // Extract IV from the beginning
        val encryptedData = combined.sliceArray(IV_SIZE until combined.size) // Encrypted data

        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(GCM_TAG_LENGTH, iv))
        return String(cipher.doFinal(encryptedData))
    }

    val loginUserFlow: Flow<LoginUser?> = dataStoreManager.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LOGIN_USER_KEY]?.let { jsonString ->
            Json.decodeFromString<LoginUser>(jsonString)
        }
    }

    // Flow for User data
    val userFlow: Flow<User?> = dataStoreManager.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.USER_DATA_KEY]?.let { jsonString ->
            Json.decodeFromString<User>(jsonString)
        }
    }


    // Function to save LoginUser data (encrypted)
    fun saveLoginUser(loginUser: LoginUser) {
        val jsonString = Json.encodeToString(loginUser)
        dataStoreManager.saveData(DataStoreKeys.LOGIN_USER_KEY, jsonString)
    }

    // Function to get LoginUser data (plain text)
    fun getLoginUser(): LoginUser? {
        val jsonString = dataStoreManager.getData(DataStoreKeys.LOGIN_USER_KEY)
        return jsonString?.let { Json.decodeFromString<LoginUser>(it) }
    }

    // Function to delete LoginUser data
    fun deleteLoginUser() {
        dataStoreManager.deleteData(DataStoreKeys.LOGIN_USER_KEY)
    }

    // Save User data
    fun saveUser(user: User) {
        val jsonString = Json.encodeToString(user)
        dataStoreManager.saveData(DataStoreKeys.USER_DATA_KEY, jsonString)
    }

    //Save User password
    fun savePassword(Password: String) {
        val jsonString = Json.encodeToString(Password)
        dataStoreManager.saveData(DataStoreKeys.USER_PASSWORD_KEY, jsonString)
    }


    // Delete HomeScreenResponse data
    fun deleteHomeScreenResponse(){
        dataStoreManager.deleteData(DataStoreKeys.HOME_SCREEN_KEY)
    }

    // Delete LoginResponse data
    fun deleteLoginResponse(){
        dataStoreManager.deleteData(DataStoreKeys.LOGIN_RESPONSE_KEY)
    }


    fun getSavedPassword(): String? {
        val jsonString = dataStoreManager.getData(DataStoreKeys.USER_PASSWORD_KEY)
        Log.d("JSONInput", jsonString.toString()) // Log the input to see its content
        return jsonString?.let { Json.decodeFromString<String>(it) }
    }

    // Function to get the selected language
    fun getSelectedLanguage(): String {
        val jsonString = dataStoreManager.getData(DataStoreKeys.LANGUAGE_KEY)
        return jsonString ?: DEFAULT_LANGUAGE // Return the default language if not set
    }

    // Function to save the selected language
    fun saveSelectedLanguage(language: String) {
        dataStoreManager.saveData(DataStoreKeys.LANGUAGE_KEY, language)
    }
}