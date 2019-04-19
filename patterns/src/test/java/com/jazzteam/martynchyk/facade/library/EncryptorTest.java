package com.jazzteam.martynchyk.facade.library;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class EncryptorTest {

    String messageExpected;
    String messageEncrypted;

    @Test
    public void testEncrypt() {
        messageExpected = "Привет";
        messageEncrypted = Encryptor.encrypt(3, messageExpected);
        assertNotEquals(messageEncrypted, messageExpected);
    }

    @Test
    public void testDecrypt() {
        messageExpected = "Привет";
        messageEncrypted = Encryptor.encrypt(3, messageExpected);
        String decryptedMessage = Encryptor.decrypt(3, messageEncrypted);
        assertEquals(decryptedMessage, messageExpected);
    }
}