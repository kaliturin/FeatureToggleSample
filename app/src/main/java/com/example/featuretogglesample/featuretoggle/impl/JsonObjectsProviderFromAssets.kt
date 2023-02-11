package com.example.featuretogglesample.featuretoggle.impl

import android.content.res.AssetManager
import com.example.featuretogglesample.featuretoggle.JsonObjectsProvider
import com.example.featuretogglesample.featuretoggle.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Provides a list of [JSONObject] built from a content of json files read from assets
 */
class JsonObjectsProviderFromAssets(private val assetManager: AssetManager) : JsonObjectsProvider {
    private val jsonFiles: MutableList<String> = mutableListOf()

    fun addJsonFile(jsonFile: String) = apply {
        jsonFiles.add(jsonFile)
    }

    override suspend fun get(): List<JSONObject> {
        return withContext(Dispatchers.IO) {
            jsonFiles.map {
                JsonUtils.jsonObjectFromAssetsFile(assetManager, it)
            }
        }
    }
}