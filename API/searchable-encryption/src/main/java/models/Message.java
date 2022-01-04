package models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "MESSAGE")
@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "text")
    private String text;

    @Column(name = "sentDate")
    private String sentDate;

    @Column(name = "sender_id", updatable = false, insertable = true)
    private long sender_id;

    @Column(name = "receiver_id", updatable = false, insertable = true)
    private long receiver_id;

    public Message() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSender_id() {
        return sender_id;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

}
