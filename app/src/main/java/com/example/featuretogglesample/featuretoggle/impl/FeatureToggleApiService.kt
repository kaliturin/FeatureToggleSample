package com.example.featuretogglesample.featuretoggle.impl

import retrofit2.http.GET
import retrofit2.http.Query

interface FeatureToggleApiService {
    @GET("config")
    suspend fun requestJsonMap(
        @Query("accessType") accessType: String,
        @Query("mark") mark: String,
        @Query("currentAppVersion") currentAppVersion: String,
        @Query("UUID") UUID: String
    ): Map<String, Any>
}