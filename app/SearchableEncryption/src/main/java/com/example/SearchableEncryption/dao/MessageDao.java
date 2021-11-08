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
    public List<Message> getAllMessagesBySender(String sender) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiver(String receiver) { return DB; }

    @Override
    public List<Message> getAllMessagesBySenderAndRead(String sender, boolean read) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiverAndRead(String receiver, boolean read) { return DB; }


    @Override
    public List<Message> getAllMessagesBySenderAndSentDate(String sender, String sentDate) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiverAndSentDate(String receiver, String sentDate) { return DB; }

    @Override
    public List<Message> getAllMessagesBySenderAndReadDate(String sender, String readDate) { return DB; }

    @Override
    public List<Message> getAllMessagesByReceiverAndReadDate(String receiver, String readDate) {return DB; }

    @Override
    public List<Message> getAllMessagesBySenderAndTitle(String sender, String title) {return DB; }

    @Override
    public List<Message> getAllMessagesByReceiverAndTitle(String receiver, String title) {return DB; }

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
