package com.mwafaimk.unify.core.util.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object AuthPreferencesKeys {
    val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
}