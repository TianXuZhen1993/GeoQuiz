package com.example.geoquiz.beatbox

import android.content.res.AssetManager
import android.util.Log
import com.example.geoquiz.beatbox.bean.Sound

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/4/7 15:17
 */
class BeatBox(private val assetManager: AssetManager) {

    fun loadSounds(): List<Sound> {
        return try {
            val soundsName = assetManager.list(SOUNDS_FOLDER)!!
            val soundList = mutableListOf<Sound>()
            soundsName.forEach { fileName ->
                val assetPath = "$SOUNDS_FOLDER/$fileName"
                val sound = Sound(assetPath)
                soundList.add(sound)
            }
            return soundList
        } catch (e: Exception) {
            Log.e(TAG, "Could not list assets", e)
            emptyList()
        }
    }
}

private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"