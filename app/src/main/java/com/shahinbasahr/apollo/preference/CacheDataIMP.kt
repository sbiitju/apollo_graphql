package com.shahinbasahr.apollo.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import com.shahinbasahr.apollo.location.model.UserSelectedLocation
import java.lang.reflect.Type
import javax.inject.Inject

class CacheDataIMP @Inject constructor(
    private val sharedPreferences: SharedPreferences
):CacheData {
    val gson by lazy { GsonBuilder().create() }
    companion object{
        val KEY_USER_LOCATION="user_location"
    }
    override var userLocation: UserSelectedLocation?
        get() = getObject<UserSelectedLocation>(KEY_USER_LOCATION, UserSelectedLocation::class.java)
        set(value) {
            value?.let {
                saveObject(KEY_USER_LOCATION, it)
            }
        }

    override fun <T> getObject(key: String, type: Type): T? {
        val json = getString(key)
        return gson.fromJson<T>(json, type)
    }

    override fun saveObject(key: String, o: Any) {
        putString(key, gson.toJson(o))
    }
    private fun getString(key: String, default: String? = null): String? {
        return sharedPreferences.getString(key, default)
    }

    private fun putString(key: String, value: String?) {
        value?.let {
            sharedPreferences.edit { putString(key, it) }
        }
    }
}