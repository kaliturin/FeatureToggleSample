package com.example.featuretogglesample.featuretoggle

interface FeatureToggleJsonMapRequestArgsProvider {
    fun getAccessType(): String
    fun getMark(): String
    fun getCurrentAppVersion(): String
    fun getUUID(): String
}