package com.example.geoquiz.beatbox.bean

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/4/7 16:47
 */
class Sound(val assetPath: String) {
    val name = assetPath.split("/").last().removeSuffix(".wav")
}