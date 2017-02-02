package me.itchalleges.notwitter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Date: 11/05/2016 12:50 PM
 */
public class Sha2Util {
    public static String sha2(String password){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
