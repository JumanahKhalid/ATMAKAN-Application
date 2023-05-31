package com.example.atmakan.chat;

public class ChatItem {

    private String message;
    private String receiveId;
    private String senderID;
    private String date;

    public ChatItem(String message, String receiveId, String senderID, String date) {
        this.message = message;
        this.receiveId = receiveId;
        this.senderID = senderID;
        this.date = date;
    }

    public ChatItem() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
