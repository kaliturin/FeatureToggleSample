package com.example.featuretogglesample.featuretoggle.di

import android.app.Application
import com.example.featuretogglesample.featuretoggle.*
import com.example.featuretogglesample.featuretoggle.impl.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

object FeatureToggleKoinModule {

    fun startKoin(application: Application) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(application)
            modules(listOf(module()))
        }
    }

    private fun module() = module {
        single<JsonMapsProvider> { JsonMapsProviderImpl() }

        single<JsonObjectsProvider> {
            val assets = JsonObjectsProviderFromAssets(androidContext().assets)
                .addJsonFile(JSON_DEFAULTS)
                .addJsonFile(JSON_SPECIFIC)

            val maps = JsonObjectsProviderFromMaps(get())

            JsonObjectsProviderAggregator(assets, maps)
        }

        single<FeatureToggleBuilder> { FeatureToggleBuilderFromJson(get()) }

        single<FeatureToggleProvider<FeatureToggle>> {
            FeatureToggleProviderImpl(FeatureToggle::class, get())
        }
    }

    private const val JSON_DEFAULTS = "featuresDefault.json"
    private const val JSON_SPECIFIC = "featuresSpecific.json"
}