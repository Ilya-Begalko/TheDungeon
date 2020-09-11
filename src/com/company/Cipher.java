package com.company;

public class Cipher
{
    /* Случайный ключ для шифрования и дешифрования */
    private static final int KEY = 947;

    public static String encrypt(String data)
    {
        String encrypted = "";

        for (int i = 0; i < data.length(); i++)
        {
            encrypted += (char) ((int) data.charAt(i) + KEY);
        }

        return encrypted;
    }

    public static String decrypt(String message)
    {
        String decrypted = "";

        for (int i = 0; i < message.length(); i++)
        {
            decrypted += (char) ((int) message.charAt(i) - KEY);
        }

        return decrypted;
    }
}
