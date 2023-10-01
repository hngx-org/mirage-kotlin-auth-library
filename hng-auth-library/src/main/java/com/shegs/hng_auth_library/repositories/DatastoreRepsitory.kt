package com.shegs.hng_auth_library.repositories

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreRepository(context: Context) {
    private val userPreferencesDataStore = context.dataStore

    companion object {
        val USER_ID_KEY = stringPreferencesKey("USER_ID_KEY")
    }

    suspend fun saveUserID(userID: String) {
        userPreferencesDataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userID
        }
        Log.d("SAVE USER ID", "saveUserID: $userID")
    }

    fun getUserID() = userPreferencesDataStore.data.catch { exception ->
        if (exception is Exception) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val userID = preferences[USER_ID_KEY] ?: ""
        userID
    }
}