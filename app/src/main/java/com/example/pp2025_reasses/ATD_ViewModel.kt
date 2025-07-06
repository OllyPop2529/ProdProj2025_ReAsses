package com.example.pp2025_reasses

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ATD_ViewModel(application: Application) : AndroidViewModel(application)
{
    private val context: Context = application.applicationContext

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    //Ported from Previous code
    private val sharedPrefs by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun checkPassword(password: String) {
        val stored = sharedPrefs.getString("password", null)
        if (stored != null && stored == password) {
            _isAuthenticated.value = true
        }
    }

    fun savePassword(password: String) {
        sharedPrefs.edit().putString("password", password).apply()
        _isAuthenticated.value = true
    }
}
