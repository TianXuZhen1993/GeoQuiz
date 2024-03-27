package com.example.geoquiz.utils;

import java.io.File;
import java.io.IOException;

/**
 * File 操作工具类
 *
 * @author TXZ
 * @version 1.0
 */
public class FileUtils {

    /**
     * 创建文件，父类不存在，先创建父类文件夹，只能创建文件，不能创建文件夹
     *
     * @param fileName 文件名称
     * @return 文件存在的文件
     */
    public static File createFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return file;
        }
        if (file.getParentFile()==null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除 文件夹 或者 文件
     *
     * @param file 文件
     */
    public static void deleteFileOrDir(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFileOrDir(f);
                }
            }
        }
        file.delete();
    }

}
