package com.example.geoquiz.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 */
public class MD5Utils {

    /**
     * 获取 字符串的MD5 加密
     *
     * @param msg 字符串
     * @return 返回MD5加密后的32位展示效果
     */
    public static String MD5(String msg) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(msg.getBytes(StandardCharsets.UTF_8));
            //获取16个byte 字节 ，每个字节用2位的16进制表示  ，总共32位
            StringBuilder hex = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                // 当一个字节小于16 的时候，为了长度为2 ，高位补充为0
                // b & 0xff 只记录它的补位
                if ((b & 0xff) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xff));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
