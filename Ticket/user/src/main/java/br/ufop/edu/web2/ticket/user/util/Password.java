package br.ufop.edu.web2.ticket.user.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;


public class Password {

    private static SecretKeySpec getKeyFromUUID(UUID uuid) throws Exception {
        byte[] key = uuid.toString().getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // AES-128 bit key
        return new SecretKeySpec(key, "AES");
    }

    public static String encrypt(UUID uuid, String plainText) throws Exception {
        SecretKeySpec secretKey = getKeyFromUUID(uuid);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // modo simples, não recomendado para produção
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(UUID uuid, String encryptedText) throws Exception {
        SecretKeySpec secretKey = getKeyFromUUID(uuid);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
