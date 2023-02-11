package com.example.featuretogglesample.featuretoggle

interface JsonMapsProvider {
    suspend fun get(): List<Map<String, Any>>
}