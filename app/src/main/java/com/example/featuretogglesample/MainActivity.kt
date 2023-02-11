package com.example.featuretogglesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.featuretogglesample.featuretoggle.di.FeatureToggleKoinModule
import com.example.featuretogglesample.ui.main.MainFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // init Timber
        Timber.plant(Timber.DebugTree())
        // init Koin
        FeatureToggleKoinModule.startKoin(application)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}