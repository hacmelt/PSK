package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClientDAO {
    @Inject
    private EntityManager em;

    public void create(Client client) {
        em.persist(client);
    }

    public List<Client> getAllClients() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }
}
