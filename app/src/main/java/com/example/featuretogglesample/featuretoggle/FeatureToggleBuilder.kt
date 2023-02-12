package com.example.featuretogglesample.featuretoggle

import kotlin.reflect.KClass

interface FeatureToggleBuilder {
    suspend fun <T : Any> build(toggleClass: KClass<T>): T
}