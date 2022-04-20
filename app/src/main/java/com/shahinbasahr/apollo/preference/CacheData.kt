package com.shahinbasahr.apollo.preference

import com.shahinbasahr.apollo.location.model.UserSelectedLocation
import java.lang.reflect.Type

interface CacheData {
    var userLocation: UserSelectedLocation?
    fun <T> getObject(key: String, type: Type): T?
    fun saveObject(key: String, o: Any)
}