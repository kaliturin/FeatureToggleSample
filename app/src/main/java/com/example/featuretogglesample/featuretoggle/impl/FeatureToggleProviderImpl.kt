package com.example.featuretogglesample.featuretoggle.impl

import com.example.featuretogglesample.featuretoggle.FeatureToggleBuilder
import com.example.featuretogglesample.featuretoggle.FeatureToggleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

class FeatureToggleProviderImpl<T : Any>(
    private val toggleClass: KClass<T>,
    private val builder: FeatureToggleBuilder
) : FeatureToggleProvider<T> {

    @Volatile
    private var featureToggle: T? = null
    private val mutex = Mutex()

    private suspend fun provide(): T {
        return mutex.withLock {
            featureToggle ?: run {
                builder.build(toggleClass).also {
                    featureToggle = it
                }
            }
        }
    }

    override suspend fun get(): T {
        return withContext(Dispatchers.IO) {
            provide()
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <S : Any> get(sectionClass: KClass<S>): S {
        return withContext(Dispatchers.IO) {
            val field = toggleClass.java.declaredFields
                .find { it.type == sectionClass.java }
                ?: throw IllegalArgumentException(
                    "${toggleClass.qualifiedName} doesn't have field instance of ${sectionClass.qualifiedName}"
                )
            field.isAccessible = true
            field[provide()] as S
        }
    }
}