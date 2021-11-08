package com.example.SearchableEncryption.services;

import com.example.SearchableEncryption.dao.IMessageDao;
import com.example.SearchableEncryption.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final IMessageDao messageDao;

    @Autowired
    public MessageService(@Qualifier("message_postgres") IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public int addMessage(Message message){
        return messageDao.insertMessage(message);
    }

    public List<Message> getAllMessages(){
        return messageDao.getAllMessages();
    }

    public Optional<Message> getMessageById(long id) {
        return messageDao.getMessageById(id);
    }

    public int deleteMessage(long id) {
        return messageDao.deleteMessageById(id);
    }

    public int updateMessage(long id, Message newMessage) {
        return messageDao.updateMessageById(id, newMessage);
    }

}
