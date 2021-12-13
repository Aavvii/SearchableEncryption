package apis.message;

import models.Account;
import models.Message;
import models.Status;
import repos.UserMapper;
import repos.interfaces.IAccountMapper;
import repos.interfaces.IMessageMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("/messages") // --> Resource Identifier
public class MessageResource {

    @Inject
    protected IMessageMapper messageMapper;

    @GET
    @Path("/sender/{senderusername}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessagesBySender(@QueryParam("senderusername") String senderusername) {
        return messageMapper.getAllMessagesBySender(senderusername);
    }

    @GET
    @Path("/sender/{senderusername}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageSentDateBySender(@QueryParam("senderusername") String senderusername, @QueryParam("id") long id) {
        return messageMapper.getMessageSentDateBySender(senderusername, id);
    }

    @GET
    @Path("/receiver/{receiverusername}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageSentDateByReceiver(@QueryParam("senderusername") String receiverusername, @QueryParam("id") long id) {
        return messageMapper.getMessageSentDateByReceiver(receiverusername, id);
    }

    @GET
    @Path("/category/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessagesCategory(@QueryParam("messageId") long messageId) {
        return messageMapper.getMessagesCategory(messageId);
    }

    @GET
    @Path("/body/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessagesText(@QueryParam("messageId") long messageId) {
        return messageMapper.getMessagesText(messageId);
    }

    @GET
    @Path("/status/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean getMessageReadStatus(@QueryParam("messageId") long messageId) {
        return messageMapper.getMessageReadStatus(messageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createNewMessage(Message message) {
        return messageMapper.createNewMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("messageId") long messageId) {
        if (messageMapper.deleteMessage(messageId)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
