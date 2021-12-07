package repos.interfaces;

import models.Message;

import java.util.List;

public interface IMessageMapper {

    List<Message> getAllMessagesBySender(String sender);

    String getMessageSentDateBySender(String sender, long messageId);
    String getMessageSentDateByReceiver(String receiver, long messageId);

    String getMessagesCategory(long messageId);
    String getMessagesText(long messageId);

    boolean getMessageReadStatus(long messageId);
    boolean createNewMessage(Message message);
    boolean deleteMessage(long messageId);
}
