package com.example.featuretogglesample.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.featuretogglesample.R
import com.example.featuretogglesample.featuretoggle.FeatureToggle
import com.example.featuretogglesample.featuretoggle.FeatureToggleProvider
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main) {
    private val featureToggleProvider by inject<FeatureToggleProvider<FeatureToggle>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button).setOnClickListener {
            testFeatureToggle()
        }
    }

    private fun testFeatureToggle() {
        lifecycleScope.launch {
            val root = featureToggleProvider.get()
            Timber.d("Feature toggle root = \n $root")
            Timber.d("Feature toggle analytics.contentTracking = ${root.analytics.contentTracking}")
            val analytics = featureToggleProvider.get(FeatureToggle.Analytics::class)
            Timber.d("Feature toggle analytics section = $analytics")
            Timber.d("Feature toggle analytics.contentTracking = ${analytics.contentTracking}")
        }
    }
}