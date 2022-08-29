package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.entity.Event;
import app.entity.Subscribe;
import app.entity.User;
import app.web.service.interfacas.*;
import app.entity.Periodical;

import java.util.ArrayList;
import java.util.List;

/**
 * The class, that represents DAO for alert users about updates in their subscribes.
 */
public class ReaderAlertServiceImpl implements Observable, ReaderAlertService {

    private final PeriodicalDAO periodicalDAO;
    private final UserService userService;
    private final SubscribeService subscribeService;

    /**
     * Constructor for ReaderAlertServiceImpl.
     *
     * @param periodicalDAO    entity of PeriodicalDAOImpl
     * @param userService      entity of UserService
     * @param subscribeService entity of SubscribeService
     */
    public ReaderAlertServiceImpl(PeriodicalDAO periodicalDAO, UserService userService, SubscribeService subscribeService) {
        this.periodicalDAO = periodicalDAO;
        this.userService = userService;
        this.subscribeService = subscribeService;
    }

    @Override
    public void update (Periodical p) throws DbException {
        Periodical periodical = periodicalDAO.getPeriodicalById(p.getId());
        periodical.setIssue(p.getIssue());
        boolean updateIssue = periodicalDAO.updateIssue(periodical);

            if (updateIssue) {
               notifyObserver(new Event(periodical));
            }
    }

    @Override
    public List<Observer> findObserver(int eventType) throws DbException {
        Periodical periodical = periodicalDAO.getPeriodicalById(eventType);
        List<User> users = null;
        List<Subscribe> subscribeList = null;
        try {
            subscribeList = subscribeService.getSubscribesByPeriodical(periodical);
            users = userService.findUsersOfSubscribe(subscribeList);
        } catch (DbException e) {
            throw new DbException(e);
        }
        return new ArrayList<>(users);
    }

    @Override
    public void notifyObserver(Event event) throws DbException {
        List<Observer> observers = findObserver(event.getEventType());
        for (Observer o: observers){
            o.update(event);
            userService.updateUser((User) o);
        }
    }
}
