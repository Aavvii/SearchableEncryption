package searchableencryption.searchable;

import models.Message;
import repos.interfaces.IMessageMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/messages")
public class MessageResource {

    @Inject
    protected IMessageMapper messageMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAllMessages() {
        return messageMapper.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessageById(@PathParam("messageId") long messageId) {
        return messageMapper.getMessageById(messageId);
    }

    @GET
    @Path("/sentdate/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessageSentDate(@PathParam("messageId") long messageId) {
        return messageMapper.getMessageSentDateById(messageId);
    }

    @GET
    @Path("/category/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessagesCategory(@PathParam("messageId") long messageId) {
        return messageMapper.getMessagesCategory(messageId);
    }

    @GET
    @Path("/body/{messageId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessagesText(@PathParam("messageId") long messageId) {
        return messageMapper.getMessagesText(messageId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMessage(Message message) {
        if (messageMapper.createNewMessage(message)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{messageId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMessage(@PathParam("messageId") long messageId) {
        if (messageMapper.deleteMessage(messageId)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
