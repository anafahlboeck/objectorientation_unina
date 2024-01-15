package com.example.objectorientation.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHasher {

    private static final byte[] STATIC_SALT = {-13, 127, 35, -45, 67, -89, 23, 45, 67, -12, 90, -34, -67, 23, 56, -90};

    public static String hashPassword(String password) {
        int iterations = 10000;
        int keyLength = 256;

        char[] passwordChars = password.toCharArray();
        byte[] salt = STATIC_SALT;

        KeySpec spec = new PBEKeySpec(passwordChars, salt, iterations, keyLength);

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hashedPassword = keyFactory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
