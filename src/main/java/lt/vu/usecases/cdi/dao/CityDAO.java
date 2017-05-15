package lt.vu.usecases.cdi.dao;

import lt.vu.entities.City;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CityDAO {
    @Inject
    private EntityManager em;

    public void create(City city) {
        em.persist(city);
    }

    public List<City> getAllCities() {
        return em.createNamedQuery("City.findAll", City.class).getResultList();
    }
}