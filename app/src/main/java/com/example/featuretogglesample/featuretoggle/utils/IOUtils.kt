package com.example.featuretogglesample.featuretoggle.utils

import android.content.res.AssetManager

object IOUtils {
    /**
     * Returns content of a file located in the assets
     */
    fun readAssetsFileContent(assetManager: AssetManager, fileName: String): String {
        return assetManager.open(fileName).use {
            val array = ByteArray(it.available())
            if (it.read(array) > 0) String(array).trim() else ""
        }
    }
}