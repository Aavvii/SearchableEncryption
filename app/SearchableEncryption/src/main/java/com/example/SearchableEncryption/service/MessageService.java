package com.example.SearchableEncryption.service;

import com.example.SearchableEncryption.exception.ResourceNotFoundException;
import com.example.SearchableEncryption.models.Message;
import com.example.SearchableEncryption.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void save(Message message) {
        repository.save(message);
    }

    public List<Message> findAll() {
        return repository.findAll();
    }

    public Optional<Message> findById(long id) {
        return this.repository.findById(id);
    }

    public Message deleteById(long id) throws ResourceNotFoundException {
        Message message = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("The resource of class [%s] and id [%d] could not be found", Message.class.getSimpleName(), id)));
        repository.deleteById(id);
        return message;
    }

    public List<Message> getAllMessagesBySender(String user_name) {
        return repository.getAllMessagesBySender(user_name);
    }

    public List<Message> findAllByReceiver(String receiver_name) {
        return repository.findAllByReceiver(receiver_name);
    }

    public List<Message> getAllMessagesBySenderAndRead(String sender_username, boolean read) {
        return repository.getAllMessagesBySenderAndRead(sender_username, read);
    }

    public List<Message> getAllMessagesByReceiverAndRead(String receiver_username, boolean read) {
        return repository.getAllMessagesByReceiverAndRead(receiver_username, read);
    }

    public List<Message> getAllMessagesBySenderAndSentDate(String sender_username, String sentDate) {
        return repository.getAllMessagesBySenderAndSentDate(sender_username, sentDate);
    }

    public List<Message> getAllMessagesBySenderAndReadDate(String sender_username, String readDate) {
        return repository.getAllMessagesBySenderAndReadDate(sender_username, readDate);
    }

    public List<Message> getAllMessagesBySenderAndTitle(String sender_username, String title) {
        return repository.getAllMessagesBySenderAndTitle(sender_username, title);
    }

    public List<Message> getAllMessagesByReceiverAndTitle(String receiver_username, String title) {
        return repository.getAllMessagesByReceiverAndTitle(receiver_username, title);
    }

    public List<Message> getAllMessagesByReceiverAndSentDate(String receiver_username, String sentDate) {
        return repository.getAllMessagesByReceiverAndSentDate(receiver_username, sentDate);
    }

    public List<Message> getAllMessagesByReceiverAndReadDate(String receiver_username, String readDate) {
        return repository.getAllMessagesByReceiverAndReadDate(receiver_username, readDate);
    }
}
