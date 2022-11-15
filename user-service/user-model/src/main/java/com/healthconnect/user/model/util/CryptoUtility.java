package com.healthconnect.user.model.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CryptoUtility {

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

    public static String aesEncrypt(String plainText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String aesDecrypt(String encryptedText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String toBase64_unsecure(String data) {
        return toBase64_unsecure(data.getBytes());
    }

    public static String toBase64_unsecure(byte[] data) {
        return Base64.getEncoder().encodeToString((data));
    }

    public static String fromBase64_unsecure(String data) {
        return fromBase64_unsecure(data.getBytes());
    }

    public static String fromBase64_unsecure(byte[] data) {
        return new String((Base64.getDecoder().decode(data)));
    }
}

