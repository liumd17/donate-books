package com.liumd.data.utils;

import java.security.MessageDigest;

/**
 * @author liumuda
 * @date 2022/1/12 14:54
 */
public class MD5Util {
    public MD5Util() {
    }

    public static String encrypt(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte[] s = m.digest();
            String result = "";

            for(int i = 0; i < s.length; ++i) {
                result = result + Integer.toHexString(255 & s[i] | -256).substring(6);
            }

            return result;
        } catch (Exception var5) {
            var5.printStackTrace();
            return "";
        }
    }
}
