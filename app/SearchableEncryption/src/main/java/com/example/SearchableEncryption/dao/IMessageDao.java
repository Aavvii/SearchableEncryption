package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Message;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public interface IMessageDao {
    int insertMessage(long id, Message message);
    default int insertMessage(Message message) {
        long id = ThreadLocalRandom.current().nextLong();
        return insertMessage(id, message);
    }

    List<Message> getAllMessages();

    List<Message> getAllMessagesBySender(String sender);

    List<Message> getAllMessagesByReceiver(String receiver);

    List<Message> getAllMessagesBySenderAndRead(String sender, boolean read);

    List<Message> getAllMessagesByReceiverAndRead(String receiver, boolean read);

    List<Message> getAllMessagesBySenderAndSentDate(String sender, String sentDate);

    List<Message> getAllMessagesByReceiverAndSentDate(String receiver, String sentDate);

    List<Message> getAllMessagesBySenderAndTitle(String sender, String title);

    List<Message> getAllMessagesByReceiverAndTitle(String receiver, String title);

    List<Message> getAllMessagesBySenderAndReadDate(String sender, String readDate);

    List<Message> getAllMessagesByReceiverAndReadDate(String receiver, String readDate);

    int deleteMessageById(long id);

    int updateMessageById(long id, Message message);

    Optional<Message> getMessageById(long id);
}
