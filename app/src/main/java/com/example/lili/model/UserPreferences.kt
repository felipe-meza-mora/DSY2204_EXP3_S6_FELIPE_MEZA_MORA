package com.example.lili.model

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension para DataStore
val Context.dataStore by preferencesDataStore("user_preferences")

class UserPreferences(private val context: Context) {
    private val gson = Gson()
    private val USER_LIST_KEY = stringPreferencesKey("user_list_key")

    // Obtener usuarios como Flow
    val userListFlow: Flow<List<Usuario>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[USER_LIST_KEY] ?: ""
            if (json.isNotEmpty()) {
                val type = object : TypeToken<List<Usuario>>() {}.type
                gson.fromJson(json, type)
            } else {
                emptyList()
            }
        }

    // Guardar lista de usuarios
    suspend fun saveUsers(userList: List<Usuario>) {
        val json = gson.toJson(userList)
        context.dataStore.edit { preferences ->
            preferences[USER_LIST_KEY] = json
        }
    }

    private val CURRENT_USER_KEY = stringPreferencesKey("current_user_key")

    // Guardar usuario autenticado
    suspend fun saveCurrentUser(usuario: Usuario) {
        val json = gson.toJson(usuario)
        context.dataStore.edit { preferences ->
            preferences[CURRENT_USER_KEY] = json
        }
    }

    // Obtener usuario autenticado
    val currentUserFlow: Flow<Usuario?> = context.dataStore.data
        .map { preferences ->
            val json = preferences[CURRENT_USER_KEY] ?: ""
            if (json.isNotEmpty()) {
                gson.fromJson(json, Usuario::class.java)
            } else {
                null
            }
        }

    // Borrar usuario autenticado (logout)
    suspend fun clearCurrentUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(CURRENT_USER_KEY)
        }
    }
}