package com.durov.maks.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.log4j.Logger;
import com.durov.maks.cinema.exceptions.AuthenticationException;

public class HashUtil {
    private static final String HASHING_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            messageDigest.update(salt);
            byte[] digest = messageDigest.digest(password.getBytes());
            for (byte b: digest) {
                hashedPassword.append(String.format("%02x",b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new AuthenticationException("can't hashing password", e);
        }
        return hashedPassword.toString();
    }
}
