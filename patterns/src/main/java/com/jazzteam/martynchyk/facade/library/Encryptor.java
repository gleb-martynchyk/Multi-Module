package com.jazzteam.martynchyk.facade.library;

public class Encryptor {
    public static String encrypt(int key, String massage) {
        char temp;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < massage.length(); i++) {
            temp = massage.charAt(i);
            temp += key;
            builder.append(temp);
        }
        return builder.toString();
    }

    public static String decrypt(int key, String message) {
        char temp;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            temp = message.charAt(i);
            temp -= key;
            builder.append(temp);
        }
        return builder.toString();
    }
}
