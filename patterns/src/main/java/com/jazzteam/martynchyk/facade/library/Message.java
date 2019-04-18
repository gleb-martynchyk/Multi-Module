package com.jazzteam.martynchyk.facade.library;

public class Message {
    private String massage;

    public Message(String massage) {
        this.massage = massage;
    }

    public void send(String adress) {
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
