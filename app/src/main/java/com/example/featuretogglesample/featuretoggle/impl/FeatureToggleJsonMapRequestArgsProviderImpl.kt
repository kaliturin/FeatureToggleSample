package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.Config
import com.example.featuretogglesample.featuretoggle.FeatureToggleJsonMapRequestArgsProvider

class FeatureToggleJsonMapRequestArgsProviderImpl(private val config: Config) :
    FeatureToggleJsonMapRequestArgsProvider {
    override fun getAccessType(): String = config.getAccessType()
    override fun getMark(): String = config.getMark()
    override fun getCurrentAppVersion(): String = config.getCurrentAppVersion()
    override fun getUUID(): String = config.getUUID()
}