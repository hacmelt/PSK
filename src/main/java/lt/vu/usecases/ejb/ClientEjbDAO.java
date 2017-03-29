package lt.vu.usecases.ejb;

import lt.vu.entities.Client;
import lt.vu.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;
import java.util.List;

@Stateless
public class ClientEjbDAO {
    @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    public void create(Client client) {
        em.persist(client);
    }

    public List<Client> getAllClients() {
        return em.createNamedQuery("Client.findAll", Client.class).getResultList();
    }
}
