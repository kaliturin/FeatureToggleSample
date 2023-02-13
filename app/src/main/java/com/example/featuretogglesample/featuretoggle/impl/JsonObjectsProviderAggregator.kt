package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.JsonObjectsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

/**
 * Aggregates [JSONObject] lists provided by the passed [JsonObjectsProvider] into common list
 */
class JsonObjectsProviderAggregator(
    private vararg val providers: JsonObjectsProvider
) : JsonObjectsProvider {
    override suspend fun get(): List<JSONObject> {
        return withContext(Dispatchers.Default) {
            mutableListOf<JSONObject>().apply {
                providers.forEach {
                    try {
                        addAll(it.get())
                    } catch (e: Exception) {
                        Timber.e(e)
                    }
                }
            }
        }
    }
}