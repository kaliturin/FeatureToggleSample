package com.example.featuretogglesample.featuretoggle.di

import android.app.Application
import com.example.featuretogglesample.featuretoggle.*
import com.example.featuretogglesample.featuretoggle.impl.*
import com.example.featuretogglesample.featuretoggle.utils.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


object FeatureToggleKoinModule {

    suspend fun startKoin(application: Application) {
        withContext(Dispatchers.Default) {
            startKoin {
                androidLogger(Level.DEBUG)
                androidContext(application)
                modules(listOf(module()))
            }
        }
    }

    private fun module() = module {

        single<Config> { ConfigImpl() }

        single {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(get<Config>().getServerUrl())
                .client(get())
                .addConverterFactory(JacksonConverterFactory.create(JsonUtils.jsonObjectMapper))
                .build()
        }

        single { get<Retrofit>().create(FeatureToggleApiService::class.java) }

        single<FeatureToggleJsonMapRequestArgsProvider> {
            FeatureToggleJsonMapRequestArgsProviderImpl(get())
        }

        single<JsonMapsProvider> { FeatureToggleJsonMapsProvider(get(), get()) }

        single<JsonObjectsProvider> {
            val assets = JsonObjectsProviderFromAssets(androidContext().assets)
                .addJsonFile(get<Config>().getJsonDefaults())
                .addJsonFile(get<Config>().getJsonSpecific())

            val maps = JsonObjectsProviderFromMaps(get())

            JsonObjectsProviderAggregator(assets, maps)
        }

        single<FeatureToggleBuilder> { FeatureToggleBuilderFromJson(get()) }

        single<FeatureToggleProvider<FeatureToggle>> {
            FeatureToggleProviderImpl(FeatureToggle::class, get())
        }
    }
}