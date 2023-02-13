package com.example.featuretogglesample.featuretoggle

interface Config {
    fun getJsonDefaults(): String
    fun getJsonSpecific(): String
    fun getServerUrl(): String
    fun getAccessType(): String
    fun getMark(): String
    fun getCurrentAppVersion(): String
    fun getUUID(): String
}