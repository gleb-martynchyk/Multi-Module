package com.jazzteam.martynchyk.facade;

import com.jazzteam.martynchyk.facade.library.Encryptor;
import com.jazzteam.martynchyk.facade.library.Key;
import com.jazzteam.martynchyk.facade.library.Message;

public class EncryptedMessage {

    public Key sendEncryptedMessage(String message, String address) {
        Key key = new Key();
        key.generateKey();
        Message messageObj = new Message(Encryptor.encrypt(key.getKey(), message));
        messageObj.send(address);
        return key;
    }
}
