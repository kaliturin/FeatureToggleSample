package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleBuilder
import com.example.featuretogglesample.featuretoggle.FeatureToggleProvider
import kotlin.reflect.KClass

class FeatureToggleProviderImpl<T : Any>(
    private val toggleClass: KClass<T>,
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
    override suspend fun <S : Any> get(sectionClass: KClass<S>): S {
        val field = toggleClass.java.declaredFields
            .find { it.type == sectionClass.java }
            ?: throw IllegalArgumentException(
                "${toggleClass.qualifiedName} doesn't have field instance of ${sectionClass.qualifiedName}"
            )
        field.isAccessible = true
        return field[provide()] as S
    }
}