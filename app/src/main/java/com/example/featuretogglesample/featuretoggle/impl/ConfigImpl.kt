package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.Config

class ConfigImpl : Config {
    override fun getJsonDefaults(): String = "featuresDefault.json"
    override fun getJsonSpecific(): String = "featuresSpecific.json"

    // TODO: in real app this data will be getting from other providers
    override fun getServerUrl(): String = "https://example.com/"
    override fun getAccessType(): String = "293048029854086"
    override fun getMark(): String = "Mark"
    override fun getCurrentAppVersion(): String = "1.0.0"
    override fun getUUID(): String = "49680548670873"
}