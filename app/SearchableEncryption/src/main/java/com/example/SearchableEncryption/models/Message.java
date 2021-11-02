package com.example.SearchableEncryption.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sender_username")
    private String sender_username;

    @Column(name = "receiver_username")
    private String receiver_username;

    @Column(name = "message_title")
    private String message_title;

    @Column(name = "message_body")
    private String message_body;

    @Column(name = "read")
    private boolean read;

    @Column(name = "sent_date")
    private String sent_date;

    @Column(name = "read_date")
    private String read_date;

    public Message() {

    }

    public Message(@JsonProperty("id") long id,
                   @JsonProperty("sender_username") String sender_username,
                   @JsonProperty("receiver_username") String receiver_username,
                   @JsonProperty("message_title") String message_title,
                   @JsonProperty("message_body") String message_body,
                   @JsonProperty("read") boolean read,
                   @JsonProperty("sent_date") String sent_date,
                   @JsonProperty("read_date") String read_date) {
        this.id = id;
        this.sender_username = sender_username;
        this.receiver_username = receiver_username;
        this.message_title = message_title;
        this.message_body = message_body;
        this.read = read;
        this.sent_date = sent_date;
        this.read_date = read_date;

    }

//    public Message(@JsonProperty("id") long id,
//                   @JsonProperty("sender_username") String sender_username,
//                   @JsonProperty("receiver_username") String receiver_username,
//                   @JsonProperty("message_title") String message_title,
//                   @JsonProperty("message_body") String message_body,
//                   @JsonProperty("sent_date") String sent_date) {
//        this.id = id;
//        this.sender_username = sender_username;
//        this.receiver_username = receiver_username;
//        this.message_title = message_title;
//        this.message_body = message_body;
//        this.read = false;
//        this.sent_date = sent_date;
//        this.read_date = null;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender_username() {
        return sender_username;
    }

    public void setSender_username(String sender_username) {
        this.sender_username = sender_username;
    }

    public String getReceiver_username() {
        return receiver_username;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getSent_date() {
        return this.sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public String getRead_date() {
        return read_date;
    }

    public void setRead_date(String read_date) {
        this.read_date = read_date;
    }
}
