package com.example.SearchableEncryption.dao;

import com.example.SearchableEncryption.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("message_postgres")
public class MessageDataAccessService implements IMessageDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertMessage(long id, Message message) {
        return 0;
    }

    @Override
    public List<Message> getAllMessages() {
        final String sql= "SELECT * FROM Message";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySender(String sender) {
        final String sql= "SELECT * FROM Message WHERE sender = '" + sender + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiver(String receiver) {
        final String sql= "SELECT * FROM Message WHERE receiver = '" + receiver + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySenderAndRead(String sender, boolean read) {
        final String sql= "SELECT * FROM Message WHERE sender = '" + sender + "' AND read = '" + String.valueOf(read) + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiverAndRead(String receiver, boolean read) {
        final String sql= "SELECT * FROM Message WHERE receiver = '" + receiver + "' AND read = '" + String.valueOf(read) + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySenderAndSentDate(String sender, String sentDate) {
        final String sql= "SELECT * FROM Message WHERE sender = '" + sender + "' AND sentDate = '" + sentDate + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiverAndSentDate(String receiver, String sentDate) {
        final String sql= "SELECT * FROM Message WHERE receiver = '" + receiver + "' AND sentDate = '" + sentDate + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySenderAndTitle(String sender, String title) {
        final String sql= "SELECT * FROM Message WHERE sender = '" + sender + "' AND title = '" + title + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiverAndTitle(String receiver, String title) {
        final String sql= "SELECT * FROM Message WHERE receiver = '" + receiver + "' AND title = '" + title + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySenderAndReadDate(String sender, String readDate) {
        final String sql= "SELECT * FROM Message WHERE sender = '" + sender + "' AND readDate = '" + String.valueOf(readDate) + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiverAndReadDate(String receiver, String readDate) {
        final String sql= "SELECT * FROM Message WHERE receiver = '" + receiver + "' AND readDate = '" + String.valueOf(readDate) + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public int deleteMessageById(long id) {
        return 0;
    }

    @Override
    public int updateMessageById(long id, Message message) {
        return 0;
    }

    @Override
    public Optional<Message> getMessageById(long id) {
        return Optional.empty();
    }
}
