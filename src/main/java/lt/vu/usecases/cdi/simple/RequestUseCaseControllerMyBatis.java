package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.usecases.mybatis.dao.ClubMapper;
import lt.vu.usecases.mybatis.dao.ClientClubMapper;
import lt.vu.usecases.mybatis.dao.ClientMapper;
import lt.vu.usecases.mybatis.model.Client;
import lt.vu.usecases.mybatis.model.ClientClub;
import lt.vu.usecases.mybatis.model.Club;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model // tas pats kaip: @Named ir @RequestScoped
@Slf4j
public class RequestUseCaseControllerMyBatis {

    @Getter
    private Club club = new Club();
    @Getter
    private Client client = new Client();

    @Inject
    private ClientMapper clientMapper;
    @Inject
    private ClubMapper clubMapper;
    @Inject
    private ClientClubMapper clientClubMapper;
    @Getter
    private List<Client> allClients;

    @PostConstruct
    public void init() {
        loadAllClients();
    }
    @Transactional
    public void createClubClient() {
        clubMapper.insert(club);
        clientMapper.insert(client);
        ClientClub clientClub = new ClientClub();
        clientClub.setClubId(club.getId());
        clientClub.setClientId(client.getId());
        clientClubMapper.insert(clientClub);
        log.info("Maybe OK...");
    }
    private void loadAllClients() {
        allClients = clientMapper.selectAll();
    }
}
