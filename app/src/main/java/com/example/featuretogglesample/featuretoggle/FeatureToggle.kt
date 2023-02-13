package com.example.featuretogglesample.featuretoggle

/**
 * Every field of [FeatureToggle] class represents the application section.
 * Each section may content any number of fields of subsection types or boolean flags, strings and
 * numbers.
 * When you will add your own fields - make sure you init them with default value. Otherwise, an
 * exception may be thrown if some field has not been initialized by the json parser.
 * Don't add the same section (field class) more than once, else it might not be extracted by
 * [FeatureToggleProvider] because it is looking for the first found instance of the section class
 * in the [FeatureToggle] fields.
 * Data classes provide clone method facility for probable testing and logging needs.
 */
data class FeatureToggle(
    val common: Common = Common(),
    val overview: Overview = Overview(),
    val news: News = News(),
    val profile: Profile = Profile(),
    val catalog: Catalog = Catalog(),
    val market: Market = Market(),
    val analytics: Analytics = Analytics()
) {

    data class Common(
        val showBanners: Boolean = false,
        val pushesEnabled: Boolean = false
    )

    data class Overview(
        val showAppRate: Boolean = false,
        val showAppUpdate: Boolean = false,
        val showClientInfo: Boolean = false,
        val showAppServices: Boolean = false,
        val content: Content = Content()
    ) {
        data class Content(
            val showOnBoarding: Boolean = false
        )
    }

    data class News(
        val sorting: Boolean = false,
        val showAds: Boolean = false
    )

    data class Profile(
        val showFAQ: Boolean = false,
        val showPromotion: Boolean = false,
        val showAppRate: Boolean = false,
        val ShowHelp: Boolean = false,
        val settings: Settings = Settings()
    ) {
        data class Settings(
            val showNightTheme: Boolean = false,
            val showBackgroundChange: Boolean = false
        )
    }

    data class Catalog(
        val showProducts: Boolean = false,
        val showAds: Boolean = false
    )

    data class Market(
        val showNotificationButton: Boolean = false
    )

    data class Analytics(
        val contentTracking: Boolean = true
    )
}