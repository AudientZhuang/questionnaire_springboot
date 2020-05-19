package com.zqu.yiban.questionnaire.utils;

import java.security.MessageDigest;

public class EncryptUtils {

    private final static char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};  // 用来将字节转换成16进制表示的字符

    public static String getEncryption(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes("utf-8"));
            byte[] bytes = md.digest(source.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] sourceBytes) {
        StringBuffer ret = new StringBuffer(0x10 << 1);
        for (int i = 0; i < 0x10; i++) {
            ret.append(HEX_DIGITS[(sourceBytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[sourceBytes[i] & 0x0f]);
        }
        return ret.toString();
    }

}
