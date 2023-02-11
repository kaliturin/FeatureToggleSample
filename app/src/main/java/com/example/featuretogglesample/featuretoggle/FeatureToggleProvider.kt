package com.example.featuretogglesample.featuretoggle

interface FeatureToggleProvider<T> {
    /**
     * Returns the root of the feature toggle tree
     */
    suspend fun get(): T

    /**
     * Returns value of the specific section of the feature toggle by it's class.
     * Make sure you pass correct argument to the method, else one of the following exceptions will be thrown.
     * @throws IllegalArgumentException
     * @throws ClassCastException
     * @throws SecurityException
     * @param sectionClass the class of a field of [FeatureToggle] class
     */
    suspend fun <S> get(sectionClass: Class<S>): S
}