package com.jazzteam.martynchyk.facade;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class EncryptedMessageTest {

    private String message = "hello";

    @Test
    public void testSendEncryptedMessage() {
        EncryptedMessage encryptedMessage = new EncryptedMessage();
        assertNotNull(encryptedMessage.sendEncryptedMessage(message, "минск"));
    }
}