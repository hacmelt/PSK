package lt.vu.usecases.cdi.optimisticlock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Client;
import lt.vu.entities.Club;
import lt.vu.entities.City;
import lt.vu.usecases.cdi.dao.ClientDAO;
import lt.vu.usecases.cdi.dao.ClubDAO;
import lt.vu.usecases.cdi.dao.CityDAO;
import org.hibernate.Hibernate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class EditClientUseCaseController implements Serializable {

    @Getter
    private Client selectedClient;
    @Getter
    private Client conflictingClient;

    @Getter
    private List<Client> allClients;
    @Getter
    private List<City> allCities;
    @Getter
    private List<Club> allClubs;

    @Inject
    private ClientDAO clientDAO;
    @Inject
    private ClubDAO clubDAO;
    @Inject
    private CityDAO cityDAO;

    @PostConstruct
    public void init() {
        reloadAll();
    }

    public void prepareForEditing(Client client) {
        selectedClient = client;
        conflictingClient = null;
    }

    @Transactional
    public void updateSelectedClient()  {
        try {
            clientDAO.updateAndFlush(selectedClient);
            reloadAll();
        } catch (OptimisticLockException ole) {
            conflictingClient = clientDAO.findById(selectedClient.getId());
            Hibernate.initialize(conflictingClient.getClubList());
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    @Transactional
    public void overwriteClient() {
        selectedClient.setOptLockVersion(conflictingClient.getOptLockVersion());
        updateSelectedClient();
    }


    public void reloadAll() {
        allClients = clientDAO.getAllClients();
        allCities = cityDAO.getAllCities();
        allClubs = clubDAO.getAllClubs();
    }
}