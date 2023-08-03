package com.example.android.cryptoking;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class DES {

    private static Cipher encryptionCipher;
    private final SecretKey key;
    private static Cipher decryptionCipher;

    private void init() throws Exception{
        encryptionCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        decryptionCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(encryptionCipher.getIV()));
    }

    //  key generation
    public DES() throws Exception{
        this.key = generateKey();
        init();
    }

    public DES (SecretKey key) throws Exception{
        this.key = key;
        init();
    }

    public static SecretKey generateKey() throws Exception{
        return KeyGenerator.getInstance("DES").generateKey();
    }

    //  Encryption -------------->>>>>>>>>>>>>>>>>>>>>>>

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String enc(String msg) throws Exception{
        byte [] data = encryptionCipher.doFinal(msg.getBytes());
        return Base64.getEncoder().encodeToString(data);
    }


    // Decryption ------------------>>>>>>>>>>>>>>>>>>>>>
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String dec(String msg) throws Exception{
        byte [] data = Base64.getDecoder().decode(msg);
        return new String(decryptionCipher.doFinal(data));
    }

}
