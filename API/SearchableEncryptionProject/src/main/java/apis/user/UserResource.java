package apis.user;

import models.User;
import repos.interfaces.IUserMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The @Path annotation’s value is a relative URI path indicating where the Java class will
 * be hosted. You can also embed variables in the URIs to make a URI path template.
 */
@Path("/users") // --> Resource Identifier
public class UserResource {

    @Inject
    protected IUserMapper userMapper;

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public User getAccountById(@PathParam("id") int id) {
        return userMapper.getUserById(id);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public User getAccountById(@PathParam("email") String email) {
        return userMapper.getUserBy(email);
    }

    @GET
    @Path("/{username}/{password}")
    @Produces(MediaType.TEXT_PLAIN)
    public User getAccountById(@PathParam("username") String username, @PathParam("password") String password) {
        return userMapper.getUserBy(username, password);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createUser(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("password") String email) {
        return userMapper.createNew(new User(username, password, email, "guest"));
    }

    /**
     * Build Response instances that contain
     * metadata instead of or in addition to an entity
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        if (userMapper.updateUser(user)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") long userId) {
        if (userMapper.deleteUser(userId)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
