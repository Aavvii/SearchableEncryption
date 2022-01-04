package repos;

import models.Account;
import models.Message;
import repos.interfaces.IMessageMapper;

import javax.transaction.Transactional;
import java.util.List;

public class MessageMapper extends DataMapper<Message, Long> implements IMessageMapper {

    public MessageMapper() {
        super(Message.class);
    }

    @Override
    public List<Message> getAllMessages() {
        return this.findAll();
    }

    @Override
    public Message getMessageById(long messageId) {
        return this.findById(messageId);
    }

    @Override
    public List<Message> getAllMessagesBySenderId(long senderId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.sender_id = " + senderId;
        return entityManager.createQuery(qlString).getResultList();
    }

    @Override
    public String getMessageSentDateById(long messageId) {
        return this.findById(messageId).getSentDate();
    }

    @Override
    public String getMessagesCategory(long messageId) {
        return this.findById(messageId).getCategory();
    }

    @Override
    public String getMessagesText(long messageId) {
        return this.findById(messageId).getText();
    }

    @Override
    @Transactional
    public boolean createNewMessage(Message message) {
        try {
            this.create(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMessage(long messageId) {
        try {
            Message message = findById(messageId);
            if (message != null) {
                this.remove(message);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
