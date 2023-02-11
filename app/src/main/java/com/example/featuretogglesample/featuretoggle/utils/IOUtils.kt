package com.example.featuretogglesample.featuretoggle.utils

import android.content.res.AssetManager
import java.io.InputStream

object IOUtils {
    /**
     * Returns content of the file, located in the assets, as a string
     */
    fun readAssetsFileContent(assetManager: AssetManager, fileName: String): String {
        var json = String()
        var inputStream: InputStream? = null
        try {
            try {
                inputStream = assetManager.open(fileName)
                val array = ByteArray(inputStream.available())
                if (inputStream.read(array) > 0) json = String(array)
            } catch (ignored: Exception) {
            } finally {
                inputStream?.close()
            }
        } catch (ignored: Exception) {
        }
        return json.trim()
    }
}