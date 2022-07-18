package com.example.reposearchapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.reposearchapp.BuildConfig
import com.example.reposearchapp.data.Result
import com.example.reposearchapp.data.entity.AccessTokenRequest
import com.example.reposearchapp.data.remote.AccessService
import com.example.reposearchapp.data.safeApiCall
import com.example.reposearchapp.util.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import java.io.IOException
import javax.inject.Inject

class AccessTokenRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val tokenKey = stringPreferencesKey(ACCESS_TOKEN_KEY)

    val token: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.mapNotNull { preferences ->
        preferences[tokenKey]
    }

    suspend fun getToken(code: String): Event {
        val result = safeApiCall {
            AccessService.api.requestAccessToken(
                AccessTokenRequest(
                    clientId = BuildConfig.GITHUB_CLIENT_ID,
                    clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
                    code = code
                )
            )
        }

        return when (result) {
            is Result.Success -> {
                saveToken(result.data.accessToken)
                Event.Success(result.data.accessToken)
            }
            is Result.Error -> {
                Event.Error(result.exception)
            }
        }
    }

    private suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    suspend fun removeToken() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        const val ACCESS_TOKEN_DATA_STORE = "accessTokenDataStore"
        const val ACCESS_TOKEN_KEY = "accessToken"
    }
}