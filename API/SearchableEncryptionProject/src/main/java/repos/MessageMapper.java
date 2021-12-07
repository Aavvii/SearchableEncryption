package repos;

import models.Account;
import models.Message;
import models.User;
import repos.interfaces.IMessageMapper;

import javax.transaction.Transactional;
import java.util.List;

public class MessageMapper extends DataMapper<Message, Long> implements IMessageMapper {

    public MessageMapper(){
        super(Message.class);
    }

    @Override
    public List<Message> getAllMessagesBySender(String sender) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.sender = " + sender;
        return entityManager.createQuery(qlString).getResultList();
    }

    @Override
    public String getMessageSentDateBySender(String sender, long messageId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + messageId + " AND e.receiver=" + sender;
        Message result = (Message) entityManager.createQuery(qlString).getSingleResult();
        return result.getSender();
    }

    @Override
    public String getMessageSentDateByReceiver(String receiver, long messageId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + messageId + " AND e.receiver=" + receiver;
        Message result = (Message) entityManager.createQuery(qlString).getSingleResult();
        return result.getReceiver();
    }

    @Override
    public String getMessagesCategory(long messageId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + messageId;
        Message result = (Message) entityManager.createQuery(qlString).getSingleResult();
        return result.getCategory();
    }

    @Override
    public String getMessagesText(long messageId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + messageId;
        Message result = (Message) entityManager.createQuery(qlString).getSingleResult();
        return result.getText();
    }

    @Override
    public boolean getMessageReadStatus(long messageId) {
        String qlString = "select e from " + entityClass.getSimpleName() + " e WHERE e.id = " + messageId;
        Message result = (Message) entityManager.createQuery(qlString).getSingleResult();
        return result.isRead();
    }

    @Override
    @Transactional
    public boolean createNewMessage(Message message) {
        try{
            this.create(message);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteMessage(long messageId) {
        try{
            Message message = findById(messageId);
            if (message != null) {
                this.remove(message);
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
