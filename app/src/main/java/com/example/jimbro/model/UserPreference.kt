package com.example.jimbro.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.jimbro.api.request.BmiRequest
import com.example.jimbro.api.responses.UpdateBMI
import com.example.jimbro.api.responses.User

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[EMAIL] ?: "",
                preferences[AGE] ?: 0,
                preferences[HEIGHT] ?: 0,
                preferences[WEIGHT] ?: 0,
                preferences[INTENSITY] ?: "",
                preferences[GENDER] ?: ""
            )
        }
    }

    fun getEditUser(): Flow<EditUser> {
        return dataStore.data.map { preferences ->
            EditUser(
                preferences[EMAIL] ?: "",
                preferences[AGE] ?: 0,
                "",
            )
        }
    }

    fun getEditBmi(): Flow<EditBmi> {
        return dataStore.data.map { preferences ->
            EditBmi(
                preferences[HEIGHT] ?: 0,
                preferences[WEIGHT] ?: 0,
                preferences[INTENSITY] ?: ""
            )
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL].toString()
        }
    }


    suspend fun login(  login: User) {
        dataStore.edit { preferences ->


            preferences[EMAIL] = login.email
            preferences[AGE] = login.age
            preferences[HEIGHT] = login.height
            preferences[WEIGHT] = login.weight
            preferences[INTENSITY] = login.intensity
            preferences[GENDER] = login.gender


        }
    }
    suspend fun updateBmi(  updateBMI: BmiRequest) {
        dataStore.edit { preferences ->

            preferences[HEIGHT] = updateBMI.height
            preferences[WEIGHT] = updateBMI.weight
            preferences[INTENSITY] = updateBMI.intensity!!
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[EMAIL] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val EMAIL = stringPreferencesKey("email")
        private val AGE = intPreferencesKey("age")
        private val HEIGHT =  intPreferencesKey("height")
        private val WEIGHT =  intPreferencesKey("weight")
        private val INTENSITY = stringPreferencesKey("intensity")
        private val GENDER = stringPreferencesKey("gender")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
//class UserPreference private constructor(private val context: Context) {
//
//    companion object{
//        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
//        val TOKEN_KEY = stringPreferencesKey("token")
//    }
//
//}