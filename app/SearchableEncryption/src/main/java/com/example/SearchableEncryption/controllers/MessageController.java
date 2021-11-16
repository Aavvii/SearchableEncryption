package com.example.SearchableEncryption.controllers;

import com.example.SearchableEncryption.exception.ResourceNotFoundException;
import com.example.SearchableEncryption.models.Message;
import com.example.SearchableEncryption.repository.MessageRepository;
import com.example.SearchableEncryption.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/message")
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) { this.messageService = messageService; }

    @PostMapping
    public void addMessage(@RequestBody Message message) { messageService.save(message); }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.findAll();
    }

    @GetMapping(path="sender={sender}")
    public List<Message> getAllMessagesBySender(@PathVariable("sender") String sender_username) { return messageService.getAllMessagesBySender(sender_username); }

    @GetMapping(path="sender={sender}/read={read}")
    public List<Message> getAllMessagesBySenderAndRead(@PathVariable("sender") String sender_username, @PathVariable("read") boolean read) { return messageService.getAllMessagesBySenderAndRead(sender_username, read); }

    @GetMapping(path="sender={sender}/sentDate={sentDate}")
    public List<Message> getAllMessagesBySenderAndSentDate(@PathVariable("sender") String sender_username, @PathVariable("sentDate") String sentDate) { return messageService.getAllMessagesBySenderAndSentDate(sender_username, sentDate); }

    @GetMapping(path="sender={sender}/readDate={readDate}")
    public List<Message> getAllMessagesBySenderAndReadDate(@PathVariable("sender") String sender_username, @PathVariable("readDate") String readDate) { return messageService.getAllMessagesBySenderAndReadDate(sender_username, readDate); }

    @GetMapping(path="sender={sender}/title={title}")
    public List<Message> getAllMessagesBySenderAndTitle(@PathVariable("sender") String sender_username, @PathVariable("title") String title) { return messageService.getAllMessagesBySenderAndTitle(sender_username, title); }

    @GetMapping(path="receiver={receiver}")
    public List<Message> getAllMessagesByReceiver(@PathVariable("receiver") String receiver_name) { return messageService.findAllByReceiver(receiver_name); }

    @GetMapping(path="receiver={receiver}/read={read}")
    public List<Message> getAllMessagesByReceiverAndRead(@PathVariable("receiver") String receiver_username, @PathVariable("read") boolean read) { return messageService.getAllMessagesByReceiverAndRead(receiver_username, read); }

    @GetMapping(path="receiver={receiver}/sentDate={sentDate}")
    public List<Message> getAllMessagesByReceiverAndSentDate(@PathVariable("receiver") String receiver_username, @PathVariable("sentDate") String sentDate) { return messageService.getAllMessagesByReceiverAndSentDate(receiver_username, sentDate); }

    @GetMapping(path="receiver={receiver}/readDate={readDate}")
    public List<Message> getAllMessagesByReceiverAndReadDate(@PathVariable("receiver") String receiver_username, @PathVariable("readDate") String readDate) { return messageService.getAllMessagesByReceiverAndReadDate(receiver_username, readDate); }

    @GetMapping(path="receiver={receiver}/title={title}")
    public List<Message> getAllMessagesByReceiverAndTitle(@PathVariable("receiver") String receiver_username, @PathVariable("title") String title) { return messageService.getAllMessagesByReceiverAndTitle(receiver_username, title); }

    @GetMapping(path="id={id}")
    public Message getMessageById(@PathVariable("id") long id) {
        return messageService.findById(id)
                .orElse(null);
    }

    @DeleteMapping(path="id={id}")
    public ResponseEntity<Message> deleteMessageById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(messageService.deleteById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path="id={id}")
    public void updateMessage(@PathVariable("id") long id, @Valid @NotNull @RequestBody Message messageToUpdate) {
        messageService.save(messageToUpdate);
    }
}
