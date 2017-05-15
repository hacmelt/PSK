package lt.vu.usecases.cdi.conversation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Client;
import lt.vu.entities.Club;
import lt.vu.usecases.cdi.dao.ClubDAO;
import lt.vu.usecases.cdi.dao.ClientDAO;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import javax.annotation.PostConstruct;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
@Slf4j
public class ConversationUseCaseControllerCdi implements Serializable {

    private static final String PAGE_INDEX_REDIRECT = "conversation-cdi?faces-redirect=true";

    private enum CURRENT_FORM {
        CREATE_CLUB, CREATE_CLIENT, CONFIRMATION
    }

    @Inject
    private EntityManager em;

    @Inject
    @Getter
    private Conversation conversation;

    @Inject
    private ClubDAO clubDAO;
    @Inject
    private ClientDAO clientDAO;

    @Getter
    private Club club = new Club();
    @Getter
    private Client client = new Client();
    @Getter
    private List<Client> allClients;


    private CURRENT_FORM currentForm = CURRENT_FORM.CREATE_CLUB;
    public boolean isCurrentForm(CURRENT_FORM form) {
        return currentForm == form;
    }

    @PostConstruct
    public void init() {
        loadAllClients();
    }
    /**
     * The first conversation step.
     */
    public void createClub() {
        conversation.begin();
        currentForm = CURRENT_FORM.CREATE_CLIENT;
    }

    /**
     * The second conversation step.
     */
    public void createClient() {
        client.getClubList().add(club);
        club.getClientList().add(client);
        currentForm = CURRENT_FORM.CONFIRMATION;
    }

    /**
     * The last conversation step.
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public String ok() {
        try {
            clubDAO.create(club);
            clientDAO.create(client);
            em.flush();
            Messages.addGlobalInfo("Success!");
        } catch (OptimisticLockException ole) {
            // Other user was faster...
            Messages.addGlobalWarn("Please try again");
            log.warn("Optimistic Lock violated: ", ole);
        } catch (PersistenceException pe) {
            // Some problems with DB - most often this is programmer's fault.
            Messages.addGlobalError("Finita la commedia...");
            log.error("Error ending conversation: ", pe);
        }
        Faces.getFlash().setKeepMessages(true);
        conversation.end();
        return PAGE_INDEX_REDIRECT;
    }

    /**
     * The last (alternative) conversation step.
     */
    public String cancel() {
        conversation.end();
        return PAGE_INDEX_REDIRECT;
    }

    private void loadAllClients() {
        allClients = clientDAO.getAllClients();
    }
}
