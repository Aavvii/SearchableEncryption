package searchableencryption.searchable;

import models.Account;
import repos.interfaces.IAccountMapper;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The @Path annotation’s value is a relative URI path indicating where the Java class will
 * be hosted. You can also embed variables in the URIs to make a URI path template.
 */
@Path("/accounts") // --> Resource Identifier
public class AccountResource {

    @Inject
    protected IAccountMapper accountMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts() {
        return accountMapper.getAllAccounts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountById(@PathParam("id") int id) {
        return accountMapper.getAccountById(id);
    }

    @GET
    @Path("/em/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAccountBy(@PathParam("email") String email) {
        return accountMapper.getAccountBy(email);
    }

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account login(Account account) {
        try {
            return accountMapper.getAccountBy(account.getUsername(), account.getPassword());
        } catch (NoResultException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createAccount(Account account) {
        return accountMapper.createAccount(new Account(account.getUsername(), account.getPassword(), account.getEmail(), "guest"));
    }

    /**
     * Build Response instances that contain
     * metadata instead of or in addition to an entity
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Account account) {
        if (accountMapper.updateAccount(account)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{accountId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("accountId") long accountId) {
        if (accountMapper.deleteAccount(accountId)) {
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}