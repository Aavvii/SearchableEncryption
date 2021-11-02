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
    public List<Message> getAllMessagesBySender(String sender_username) {
        final String sql= "SELECT * FROM Message WHERE sender_username = '" + sender_username + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesByReceiver(String receiver_username) {
        final String sql= "SELECT * FROM Message WHERE receiver_username = '" + receiver_username + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }

    @Override
    public List<Message> getAllMessagesBySenderAndRead(String sender_username, boolean read) {
        final String sql= "SELECT * FROM Message WHERE sender_username = '" + sender_username + "' AND read = '" + String.valueOf(read) + "'";
        List<Message> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            return new Message();
        });
        return messages;
    }
    @Override
    public List<Message> getAllMessagesByReceiverAndRead(String receiver_username, boolean read) {
        final String sql= "SELECT * FROM Message WHERE receiver_username = '" + receiver_username + "' AND read = '" + String.valueOf(read) + "'";
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
