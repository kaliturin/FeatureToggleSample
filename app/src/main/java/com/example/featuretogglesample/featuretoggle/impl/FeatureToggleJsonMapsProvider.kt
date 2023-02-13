package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleJsonMapRequestArgsProvider
import com.example.featuretogglesample.featuretoggle.JsonMapsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class FeatureToggleJsonMapsProvider(
    private val service: FeatureToggleApiService,
    private val provider: FeatureToggleJsonMapRequestArgsProvider
) : JsonMapsProvider {

    override suspend fun get(): List<Map<String, Any>> {
        return withContext(Dispatchers.IO) {
            try {
                listOf(
                    service.requestJsonMap(
                        accessType = provider.getAccessType(),
                        mark = provider.getMark(),
                        currentAppVersion = provider.getCurrentAppVersion(),
                        UUID = provider.getUUID()
                    )
                )
            } catch (e: IOException) {
                Timber.e(e)
                emptyList()
            } catch (e: HttpException) {
                Timber.e(e)
                emptyList()
            }
        }
    }
}