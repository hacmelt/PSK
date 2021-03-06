package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Club;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ClubDAO {
    @Inject
    private EntityManager em;

    public void create(Club club) {
        em.persist(club);
    }
    public List<Club> getAllClubs() {
        return em.createNamedQuery("Club.findAll", Club.class).getResultList();
    }
}
