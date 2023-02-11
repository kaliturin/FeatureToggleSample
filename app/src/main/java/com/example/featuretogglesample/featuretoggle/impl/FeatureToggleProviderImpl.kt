package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleBuilder
import com.example.featuretogglesample.featuretoggle.FeatureToggleProvider

class FeatureToggleProviderImpl<T>(
    private val toggleClass: Class<T>,
    private val builder: FeatureToggleBuilder
) : FeatureToggleProvider<T> {

    private var featureToggle: T? = null

    private suspend fun provide(): T {
        return featureToggle ?: run {
            builder.build(toggleClass).also {
                featureToggle = it
            }
        }
    }

    override suspend fun get(): T {
        return provide()
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <S> get(sectionClass: Class<S>): S {
        val toggle = provide()
        val fields = toggleClass.declaredFields
        val field = fields.find { it.type == sectionClass }
            ?: throw IllegalArgumentException(
                "${toggleClass.canonicalName} " +
                        "doesn't have field instance of ${sectionClass.canonicalName}"
            )
        field.isAccessible = true
        return field[toggle] as S
    }
}