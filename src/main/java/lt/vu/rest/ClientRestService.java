package lt.vu.rest;

import lt.vu.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientRestService {

    @Inject private EntityManager em;

    @GET
    @Path("/{clientId}")
    public Client find(@PathParam("clientId") Integer clientId) {
        return em.find(Client.class, clientId);
    }

    @Path("/create")
    @PUT
    @Transactional
    public Client create(@QueryParam("FIRST_NAME") String firstName,
                        @QueryParam("LAST_NAME") String lastName,
                        @QueryParam("REGISTRATION_NO") String registrationNo) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setRegistrationNo(registrationNo);
        em.persist(client);
        return client;
    }
    @Path("/update/{clientId}")
    @POST @Transactional
    public Response update(@PathParam("clientId") Integer id,
                           @QueryParam("FIRST_NAME") String firstName,
                           @QueryParam("LAST_NAME") String lastName,
                           @QueryParam("REGISTRATION_NO") String registrationNo) {
        Client client = em.find(Client.class, id);
        if (client == null) {
            throw new IllegalArgumentException("user id "
                    + id + " not found");
        }
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setRegistrationNo(registrationNo);
        em.merge(client);
        return Response.ok(client).build(); // low level API
    }
}