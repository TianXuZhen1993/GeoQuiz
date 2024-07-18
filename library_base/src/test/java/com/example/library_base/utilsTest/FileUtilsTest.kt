package com.example.library_base.utilsTest

import com.example.library_base.utils.FileUtils
import org.junit.Test
import java.io.File

/**
 * @version 1.0
 * @date 2024/7/18 11:10
 */
class FileUtilsTest {
    @Test
    fun fileDeleteTest() {

    }

    @Test
    fun createFileTest() {
        val file = File("D:\\aaaaaaa\\bbbb\\1111.txt")
        assert(FileUtils.createFile(file))
        val file2 = File("D:\\111.txt")
        assert(file2.createNewFile())
    }
}