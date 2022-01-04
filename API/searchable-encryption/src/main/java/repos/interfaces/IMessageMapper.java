package repos.interfaces;

import models.Message;

import java.util.List;

public interface IMessageMapper {

    List<Message> getAllMessages();

    List<Message> getAllMessagesBySenderId(long senderID);

    Message getMessageById(long messageId);

    String getMessageSentDateById(long messageId);

    String getMessagesCategory(long messageId);

    String getMessagesText(long messageId);

    boolean createNewMessage(Message message);

    boolean deleteMessage(long messageId);
}
