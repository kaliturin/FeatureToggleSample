package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleBuilder
import com.example.featuretogglesample.featuretoggle.JsonObjectsProvider
import com.example.featuretogglesample.featuretoggle.utils.JsonMerger
import com.example.featuretogglesample.featuretoggle.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

class FeatureToggleBuilderFromJson(
    private val jsonObjectsProvider: JsonObjectsProvider
) : FeatureToggleBuilder {

    override suspend fun <T> build(toggleClass: Class<T>): T {
        return withContext(Dispatchers.Default) {
            val objects = jsonObjectsProvider.get()
            // merge json objects into one
            val mergedObject = try {
                JsonMerger(objectMergeMode = JsonMerger.ObjectMergeMode.MERGE_OBJECT)
                    .merge(objects)
            } catch (e: Exception) {
                Timber.e(e)
                JSONObject()
            }
            // get the merged json content
            val jsonString = mergedObject.toString()
            // parse the json to feature toggle class
            JsonUtils.fromJson(jsonString, toggleClass)
                ?: toggleClass.newInstance()
        }
    }
}

