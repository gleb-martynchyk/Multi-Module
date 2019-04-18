package com.jazzteam.martynchyk.facade;

import com.jazzteam.martynchyk.facade.library.Key;
import com.jazzteam.martynchyk.facade.library.Message;

public class EncryptedMessage {
    private Message message;
    private Key key;

    public int sendEncryptedMessage(String message) {
        this.message = new Message(message);
        key.generateKey();
    }
}
