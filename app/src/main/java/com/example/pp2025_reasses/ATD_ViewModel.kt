package com.example.pp2025_reasses

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.pp2025_reasses.ui.theme.ActiveTheme
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ATD_ViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context = application.applicationContext

    //Authentication completion
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    //Maps
    private val _mapType = MutableStateFlow(GoogleMap.MAP_TYPE_NORMAL)
    val mapType: StateFlow<Int> = _mapType

    fun setMapType(type: Int) {
        _mapType.value = type
    }

    fun getMapType(): Int {
        return _mapType.value
    }

    //Password
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

    //Settings
    private val _isDark = MutableStateFlow(false)
    val isDark: StateFlow<Boolean> = _isDark

    fun getDark(): Boolean {
        return _isDark.value
    }

    fun setDark(bool: Boolean) {
        _isDark.value = bool
    }

    private val _isContrast = MutableStateFlow(false)
    val isContrast: StateFlow<Boolean> = _isContrast

    fun getContrast(): Boolean {
        return _isContrast.value
    }

    fun setContrast(bool: Boolean) {
        _isContrast.value = bool
    }

    private val _isClarity = MutableStateFlow(false)
    val isClarity: StateFlow<Boolean> = _isClarity

    fun getClarity(): Boolean {
        return _isClarity.value
    }

    fun setClarity(bool: Boolean) {
        _isClarity.value = bool
    }

    //LocationActivity
    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation

    fun updateUserLocation(latLng: LatLng) {
        _userLocation.value = latLng
    }

    fun getUserLocation(): String {
        return _userLocation.value.toString()
    }

    //Theme
    private val _themeMode = MutableStateFlow(ActiveTheme.LIGHT)
    val themeMode: StateFlow<ActiveTheme> = _themeMode

    fun setThemeMode(mode: ActiveTheme) {
        _themeMode.value = mode
    }
}