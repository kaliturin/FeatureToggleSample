package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.JsonMapsProvider

class JsonMapsProviderImpl : JsonMapsProvider {
    override suspend fun get(): List<Map<String, Any>> {
        //TODO this is just fake implementation
        // In real implementation make sense to get a map from network
        val map = mapOf<String, Any>("key" to "value")
        return listOf(map)
    }
}