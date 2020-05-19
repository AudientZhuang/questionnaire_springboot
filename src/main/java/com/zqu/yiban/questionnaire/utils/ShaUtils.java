package com.zqu.yiban.questionnaire.utils;

import java.security.MessageDigest;

public class ShaUtils {

    public static String getShaEncode(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(source.getBytes("utf-8"));
            byte[] bytes = md.digest(source.getBytes("utf-8"));

            StringBuilder encryString = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                int value = ((int) bytes[i]) & 0xff;
                if (value < 16) {
                    encryString.append("0");
                }
                encryString.append(Integer.toHexString(value));
            }
            return encryString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}