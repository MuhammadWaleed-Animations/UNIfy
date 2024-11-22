package com.mwafaimk.unify.core.util.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val USER_DATA_KEY = stringPreferencesKey("user")
    val HOME_SCREEN_KEY = stringPreferencesKey("home_screen")
    val LOGIN_RESPONSE_KEY = stringPreferencesKey("login_response")
    val USER_PASSWORD_KEY = stringPreferencesKey("password")
    val LOGIN_USER_KEY = stringPreferencesKey("login_user")
    val LANGUAGE_KEY = stringPreferencesKey("language")
}