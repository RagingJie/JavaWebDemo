package com.web.www.utils;

import cn.hutool.crypto.digest.DigestUtil;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Naruto
 * @description 基于hutool工具类封装的MD5工具类
 * <p>
 * 注：
 * 1、md5Hex返回的都是16进制字符串，长度为32
 * 2、md5Hex16返回的都是16进制字符串，长度为16
 */
public class Md5Util {

    /**
     * 加密字节数组
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static String encryptByte(byte[] data) {
        return DigestUtil.md5Hex(data);
    }

    /**
     * 加密字节数组
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static byte[] encryptByteToArr(byte[] data) {
        return DigestUtil.md5(data);
    }

    /**
     * 加密字符串
     * 注：
     * 默认编码方式为：UTF-8
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static String encryptStr(String data) {
        return DigestUtil.md5Hex(data);
    }

    /**
     * 加密字符串
     * 注：
     * 默认编码方式为：UTF-8
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static byte[] encryptStrToArr(String data) {
        return DigestUtil.md5(data);
    }


    /**
     * 加密字符串
     *
     * @param data    数据
     * @param charset 编码方式
     * @return md5加密后的数据
     */
    public static String encryptStr(String data, String charset) {
        return DigestUtil.md5Hex(data, charset);
    }

    /**
     * 加密字符串
     *
     * @param data    数据
     * @param charset 编码方式
     * @return md5加密后的数据
     */
    public static byte[] encryptStrToArr(String data, String charset) {
        return DigestUtil.md5(data, charset);
    }

    /**
     * 加密字符串
     *
     * @param data    数据
     * @param charset 编码方式
     * @return md5加密后的数据
     */
    public static String encryptStr(String data, Charset charset) {
        return DigestUtil.md5Hex(data, charset);
    }

    /**
     * 加密输入流
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static String encryptInputStream(InputStream data) {
        return DigestUtil.md5Hex(data);
    }

    /**
     * 加密输入流
     *
     * @param data 数据
     * @return md5加密后的数据
     */
    public static byte[] encryptInputStreamToArr(InputStream data) {
        return DigestUtil.md5(data);
    }

    /**
     * 加密文件
     *
     * @param file 文件
     * @return md5加密后的数据
     */
    public static String encryptFile(File file) {
        return DigestUtil.md5Hex(file);
    }

    /**
     * 加密文件
     *
     * @param file 文件
     * @return md5加密后的数据
     */
    public static byte[] encryptFileToArr(File file) {
        return DigestUtil.md5(file);
    }

}
