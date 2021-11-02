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

    List<Message> getAllMessagesBySender(String sender_username);

    List<Message> getAllMessagesByReceiver(String receiver_username);

    List<Message> getAllMessagesBySenderAndRead(String sender_username, boolean read);

    List<Message> getAllMessagesByReceiverAndRead(String receiver_username, boolean read);

    int deleteMessageById(long id);

    int updateMessageById(long id, Message message);

    Optional<Message> getMessageById(long id);
}
