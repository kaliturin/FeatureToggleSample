package com.example.featuretogglesample.featuretoggle

import org.json.JSONObject

interface JsonObjectsProvider {
    suspend fun get(): List<JSONObject>
}