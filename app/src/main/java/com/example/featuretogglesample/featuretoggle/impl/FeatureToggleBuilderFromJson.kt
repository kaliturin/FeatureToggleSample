package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleBuilder
import com.example.featuretogglesample.featuretoggle.JsonObjectsProvider
import com.example.featuretogglesample.featuretoggle.utils.JsonMerger
import com.example.featuretogglesample.featuretoggle.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber
import kotlin.reflect.KClass

class FeatureToggleBuilderFromJson(
    private val jsonObjectsProvider: JsonObjectsProvider
) : FeatureToggleBuilder {

    override suspend fun <T : Any> build(toggleClass: KClass<T>): T {
        return withContext(Dispatchers.Default) {
            // merge json objects into the one
            val mergedObject = try {
                JsonMerger(objectMergeMode = JsonMerger.ObjectMergeMode.MERGE_OBJECT)
                    .merge(jsonObjectsProvider.get())
            } catch (e: Exception) {
                Timber.e(e)
                JSONObject()
            }
            // parse the merged object json to build the feature toggle class
            val toggle = try {
                JsonUtils.fromJson(mergedObject.toString(), toggleClass)
            } catch (_: Exception) {
                null
            }
            toggle ?: toggleClass.java.newInstance()
        }
    }
}

