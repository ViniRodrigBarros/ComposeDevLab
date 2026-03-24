package com.example.composedevlab.core.shared.managers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class StorageManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("composedevlab_prefs", Context.MODE_PRIVATE)

    fun exists(key: String): Boolean = sharedPreferences.contains(key)

    fun getString(key: String): String? = sharedPreferences.getString(key, null)
    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)
    fun getLong(key: String): Long = sharedPreferences.getLong(key, 0L)
    fun getFloat(key: String): Float = sharedPreferences.getFloat(key, 0F)
    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun setString(key: String, value: String?) = sharedPreferences.edit { putString(key, value) }
    fun setBoolean(key: String, value: Boolean) = sharedPreferences.edit { putBoolean(key, value) }
    fun setLong(key: String, value: Long) = sharedPreferences.edit { putLong(key, value) }
    fun setFloat(key: String, value: Float) = sharedPreferences.edit { putFloat(key, value) }
    fun setInt(key: String, value: Int) = sharedPreferences.edit { putInt(key, value) }

    fun remove(key: String) = sharedPreferences.edit { remove(key) }
    fun clear() = sharedPreferences.edit { clear() }
}
