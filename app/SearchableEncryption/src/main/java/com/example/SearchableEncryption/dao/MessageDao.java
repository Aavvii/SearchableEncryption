package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("messageDao")
public class MessageDao implements IMessageDao{
    private static List<Message> DB = new ArrayList<>();

    @Override
    public int insertMessage(long id, Message message) {
        DB.add(new Message());
        return 1;
    }

    @Override
    public List<Message> getAllMessages() {
        return DB;
    }

    @Override
    public List<Message> getAllMessagesBySender(String sender_username) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiver(String receiver_username) { return DB; }

    @Override
    public List<Message> getAllMessagesBySenderAndRead(String sender_username, boolean read) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiverAndRead(String receiver_username, boolean read) { return DB; }

    @Override
    public int deleteMessageById(long id) {
        Optional<Message> messageRes = getMessageById(id);
        if(messageRes.isEmpty()){
            return 0;
        }
        DB.remove(messageRes.get());
        return 1;
    }

    @Override
    public int updateMessageById(long id, Message message) {
        return getMessageById(id)
                .map(a-> {
                    int indexOfMessageToDelete = DB.indexOf(message);
                    if (indexOfMessageToDelete >= 0) {
                        DB.set(indexOfMessageToDelete, message);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public Optional<Message> getMessageById(long id) {
        return DB.stream()
                .filter(account -> account.getId() == id)
                .findFirst();
    }
}
