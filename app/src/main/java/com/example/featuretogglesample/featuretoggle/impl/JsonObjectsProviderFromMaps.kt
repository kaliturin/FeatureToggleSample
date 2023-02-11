package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.JsonMapsProvider
import com.example.featuretogglesample.featuretoggle.JsonObjectsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

/**
 * Provides a list of [JSONObject] built from maps list provided by [JsonMapsProvider]
 */
class JsonObjectsProviderFromMaps(
    private val jsonMapsProvider: JsonMapsProvider? = null
) : JsonObjectsProvider {
    private val maps: MutableList<Map<String, Any>> = mutableListOf()

    fun addMap(map: Map<String, Any>) = apply {
        maps.add(map)
    }

    override suspend fun get(): List<JSONObject> {
        return withContext(Dispatchers.Default) {
            val list = maps.toJsonObjects().toMutableList()
            jsonMapsProvider?.get()?.toJsonObjects()?.let {
                if (it.isNotEmpty()) list.addAll(it)
            }
            list
        }
    }

    private fun List<Map<String, Any>>.toJsonObjects(): List<JSONObject> {
        return try {
            map { JSONObject(it) }
        } catch (e: Exception) {
            Timber.e(e)
            emptyList()
        }
    }
}

