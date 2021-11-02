package com.example.SearchableEncryption.controllers;

import com.example.SearchableEncryption.models.Account;
import com.example.SearchableEncryption.models.Message;
import com.example.SearchableEncryption.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/message")
@RestController
public class MessageController {
    private final MessageRepository messageRepo;

    @Autowired
    public MessageController(MessageRepository messageRepo) { this.messageRepo = messageRepo; }

    @PostMapping
    public void addMessage(@RequestBody Message message) { messageRepo.save(message); }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    @GetMapping(path="{sender_username")
    public List<Message> getAllMessagesBySender(@PathVariable("sender_username") String user_name) { return messageRepo.findAll(); }

    @GetMapping(path="{receiver_username")
    public List<Message> getAllMessagesByReceiver(@PathVariable("receiver_username") String receiver_name) { return messageRepo.findAll(); }

    @GetMapping(path="{id}")
    public Message getMessageById(@PathVariable("id") long id) {
        return messageRepo.findById(id)
                .orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteMessageById(@PathVariable("id") long id) { messageRepo. deleteById(id); }

    @PutMapping(path="{id}")
    public void updateMessage(@PathVariable("id") long id, @Valid @NotNull @RequestBody Message messageToUpdate) {
        messageRepo.save(messageToUpdate);
    }
}
