package com.sdx.lx.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * des加密解密工具
 * 
 * @author zhuliang
 */
@SuppressWarnings("restriction")
public class DESUtil {

    /**
     * 默认key
     */
    private static final String DEFAULT_KEY = "platform";
    /**
     * 编码格式
     */
    private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 
     */
    private SecretKey key;
    /**
     * 
     */
    private IvParameterSpec iv;

    /**
     * 
     */
    public DESUtil() {
        setKey(DEFAULT_KEY); // 生成密匙
    }

    /**
     * 
     */
    public DESUtil(String str) {
        setKey(str); // 生成密匙
    }

    /**
     * 根据参数生成 KEY
     */
    private void setKey(String strKey) {
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance("DES");
        } catch (Exception e1) {
            throw new RuntimeException("exception.system.0021");
        }

        try {
            DESKeySpec desKeySpec = new DESKeySpec(strKey.getBytes(DEFAULT_CHARSET));
            key = keyFactory.generateSecret(desKeySpec);
            iv = new IvParameterSpec(strKey.getBytes(DEFAULT_CHARSET));
        } catch (Exception e1) {
            throw new RuntimeException("exception.system.0022");
        }
    }

    /**
     * 加密 String 明文输入 ,String 密文输出
     */
    public String encryptStr(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes(DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("exception.system.0022");
        }
        byteMi = this.encryptByte(byteMing);
        strMi = base64en.encode(byteMi);
        
        strMi = strMi.replaceAll(" ", "");
        strMi = strMi.replaceAll("\n", "");
        strMi = strMi.replaceAll("\r", "");
        return strMi;
    }

    /**
     * 解密 以 String 密文输入 ,String 明文输出
     * 
     * @param strMi
     * @return
     */
    public String decryptStr(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = base64De.decodeBuffer(strMi);
        } catch (IOException e) {
            throw new RuntimeException("exception.system.0023");
        }
        byteMing = this.decryptByte(byteMi);
        try {
            strMing = new String(byteMing, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("exception.system.0022");
        }
        return strMing;
    }

    /**
     * 加密以 byte[] 明文输入 ,byte[] 密文输出
     * 
     * @param byteS
     * @return
     */
    private byte[] encryptByte(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException("exception.system.0024");
        }
        return byteFina;
    }

    /**
     * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
     * 
     * @param byteD
     * @return
     */
    private byte[] decryptByte(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            throw new RuntimeException("exception.system.0023");
        }
        return byteFina;
    }

}