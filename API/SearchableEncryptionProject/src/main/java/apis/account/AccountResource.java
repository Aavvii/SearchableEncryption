package apis.account;

import repos.interfaces.IAccountMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * The @Path annotation’s value is a relative URI path indicating where the Java class will
 * be hosted. You can also embed variables in the URIs to make a URI path template.
 */
@Path("/accounts") // --> Resource Identifier
public class AccountResource {

    @Inject
    protected IAccountMapper accountMapper;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int getUploadedDocuments() {
        return accountMapper.getAllAccounts();
    }

//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public UploadDocuments getUploadedDocuments(@PathParam("id") int id) {
//        return uploadDocumentsMapper.getDocumentUploadedById(id);
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public boolean uploadDocument(@QueryParam("filename") String filename, @QueryParam("user_id") long user_id) {
//        return uploadDocumentsMapper.addDocumentUpload(new UploadDocuments(filename, UUID.randomUUID().toString(), new UserMapper().getUserById(user_id)));
//    }
//
//    /**
//     * Build Response instances that contain
//     * metadata instead of or in addition to an entity
//     */
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response updateDocumentName(@PathParam("id") long id, @QueryParam("filename") String newName) {
//        UploadDocuments uploadDocument = uploadDocumentsMapper.getDocumentUploadedById (id);
//        if (uploadDocument != null) {
//            uploadDocument.setFileName(newName);
//            uploadDocumentsMapper.updateDocumentUpload(uploadDocument);
//            return Response.ok().build();
//        }
//        return Response.noContent().build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deleteDocument(@PathParam("id") long id) {
//        UploadDocuments uploadDocument = uploadDocumentsMapper.getDocumentUploadedById(id);
//        if (uploadDocument == null) {
//            return Response.noContent().build();
//        }
//        uploadDocumentsMapper.deleteDocumentUploadedById(id);
//        return Response.ok().entity("Product deleted!").build();
//    }

}