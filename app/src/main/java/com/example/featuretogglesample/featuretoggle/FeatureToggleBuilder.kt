package com.example.featuretogglesample.featuretoggle

interface FeatureToggleBuilder {
    suspend fun <T> build(toggleClass: Class<T>): T
}