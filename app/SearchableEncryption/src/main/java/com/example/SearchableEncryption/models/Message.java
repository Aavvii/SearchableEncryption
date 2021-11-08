package com.example.SearchableEncryption.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "read")
    private boolean read;

    @Column(name = "sentDate")
    private String sentDate;

    @Column(name = "readDate")
    private String readDate;

    public Message() {

    }

    public Message(@JsonProperty("id") long id,
                   @JsonProperty("sender") String sender,
                   @JsonProperty("receiver") String receiver,
                   @JsonProperty("title") String title,
                   @JsonProperty("body") String body,
                   @JsonProperty("read") boolean read,
                   @JsonProperty("sentDate") String sentDate,
                   @JsonProperty("readDate") String readDate) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.body = body;
        this.read = read;
        this.sentDate = sentDate;
        this.readDate = readDate;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender_username() {
        return sender;
    }

    public void setSender_username(String sender) {
        this.sender = sender;
    }

    public String getReceiver_username() {
        return receiver;
    }

    public void setReceiver_username(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage_title() {
        return title;
    }

    public void setMessage_title(String title) {
        this.title = title;
    }

    public String getMessage_body() {
        return body;
    }

    public void setMessage_body(String body) {
        this.body = body;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getSent_date() {
        return this.sentDate;
    }

    public void setSent_date(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getRead_date() {
        return readDate;
    }

    public void setRead_date(String readDate) {
        this.readDate = readDate;
    }
}
