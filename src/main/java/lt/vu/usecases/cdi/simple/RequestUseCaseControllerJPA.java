package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Client;
import lt.vu.entities.Club;
import lt.vu.usecases.cdi.dao.ClubDAO;
import lt.vu.usecases.cdi.dao.ClientDAO;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class RequestUseCaseControllerJPA {

    @Getter
    private Club club = new Club();
    @Getter
    private Client client = new Client();

    @Inject
    private ClubDAO clubDAO;
    @Inject
    private ClientDAO clientDAO;

    @Transactional
    public void createClientStudent() {
        client.getClubList().add(club);
        club.getClientList().add(client);
        clubDAO.create(club);
        clientDAO.create(client);
        log.info("Maybe OK...");
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }
}
