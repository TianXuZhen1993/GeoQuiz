package com.example.library_base.utils

import java.io.File
import java.io.IOException

/**
 * File 操作工具类
 *
 * @author TXZ
 * @version 1.0
 */
object FileUtils {

    fun createFile(file: File): Boolean {
        if (!file.parentFile!!.exists()) {
            file.parentFile!!.mkdirs()
        }
        return file.createNewFile()
    }

    /**
     * 删除 文件夹 或者 文件
     *
     * @param file 文件
     */
    fun deleteFileOrDir(file: File) {
        if (!file.exists()) return
        if (file.isDirectory) {
            val files = file.listFiles()
            if (files != null) {
                for (f in files) {
                    deleteFileOrDir(f)
                }
            }
        }
        file.delete()
    }
}
