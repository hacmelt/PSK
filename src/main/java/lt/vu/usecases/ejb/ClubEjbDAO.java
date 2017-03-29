package lt.vu.usecases.ejb;

import lt.vu.entities.Club;
import lt.vu.entities.Club;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;

@Stateless
public class ClubEjbDAO {
    @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED)
    private EntityManager em;

    public void create(Club club) {
        em.persist(club);
    }
}
