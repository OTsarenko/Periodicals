package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.entity.Event;
import app.entity.User;
import app.web.service.interfacas.Observable;
import app.web.service.interfacas.Observer;
import app.entity.Periodical;
import app.web.service.interfacas.ReaderAlertService;
import app.web.service.interfacas.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * The class, that represents DAO for alert users about updates in their subscribes.
 */
public class ReaderAlertServiceImpl implements Observable, ReaderAlertService {

    private final PeriodicalDAO periodicalDAO;
    private final UserService userService;

    /**
     * Constructor for ReaderAlertServiceImpl.
     *
     * @param periodicalDAO entity of PeriodicalDAOImpl
     * @param userService       entity of UserService
     */
    public ReaderAlertServiceImpl(PeriodicalDAO periodicalDAO, UserService userService) {
        this.periodicalDAO = periodicalDAO;
        this.userService = userService;
    }

    @Override
    public void update (Periodical periodical) throws DbException {
        boolean updateIssue = periodicalDAO.updateIssue(periodical);

            if (updateIssue) {
               notifyObserver(new Event(periodical));
            }
    }

    @Override
    public List<Observer> findObserver(int eventType) throws DbException {
        Periodical periodical = periodicalDAO.getPeriodicalById(eventType);
        List<User> users = null;
        try {
            users = userService.findUsersOfSubscribe(periodical);
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
        }
    }
}
