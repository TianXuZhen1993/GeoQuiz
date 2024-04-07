package com.example.geoquiz.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * 图片处理工具
 * @author: TXZ
 * @version: 1.0
 * @date: created by 2024/4/6 15:10
 */
object ImageUtils {

    /**
     * 进行图片分辨率压缩
     *
     * @param path  路径
     * @param destWidth  压缩到的宽度
     * @param destHeight 压缩到的高度
     * @return 返回图片的Bitmap
     */
    fun getScaledBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        val srcWith = options.outWidth.toFloat()
        val srcHeight = options.outHeight.toFloat()

        var inSampleSize = 1
        if (srcHeight > destHeight || srcWith > destWidth) {
            val heightScale = srcHeight / destHeight
            val widthScale = srcWith / destWidth
            inSampleSize = max(heightScale, heightScale).roundToInt()
        }
        options = BitmapFactory.Options()
        options.inSampleSize = inSampleSize
        return BitmapFactory.decodeFile(path, options)
    }

}