package com.example.SearchableEncryption.repository;

import com.example.SearchableEncryption.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllMessagesBySender(String user_name);

    List<Message> findAllByReceiver(String receiver_name);

    List<Message> getAllMessagesBySenderAndRead(String sender_username, boolean read);

    List<Message> getAllMessagesByReceiverAndRead(String receiver_username, boolean read);

    List<Message> getAllMessagesBySenderAndSentDate(String sender_username, String sentDate);

    List<Message> getAllMessagesBySenderAndReadDate(String sender_username, String readDate);
    List<Message> getAllMessagesBySenderAndTitle(String sender_username, String title);
    List<Message> getAllMessagesByReceiverAndTitle(String receiver_username, String title);

    List<Message> getAllMessagesByReceiverAndSentDate(String receiver_username, String sentDate);

    List<Message> getAllMessagesByReceiverAndReadDate(String receiver_username, String readDate);
}
