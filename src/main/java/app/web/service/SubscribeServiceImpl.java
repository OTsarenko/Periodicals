package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.dao.interfaces.SubscribeDAO;
import app.dao.interfaces.UserDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;
import app.web.service.interfacas.SubscribeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubscribeServiceImpl implements SubscribeService {

    private final SubscribeDAO subscribeDAO;
    private final UserDAO userDAO;
    private final PeriodicalDAO periodicalDAO;

    public SubscribeServiceImpl(SubscribeDAO subscribeDAO, UserDAO userDAO, PeriodicalDAO periodicalDAO) {
        this.subscribeDAO = subscribeDAO;
        this.userDAO = userDAO;
        this.periodicalDAO = periodicalDAO;
    }

    @Override
    public void insertSubscribe(User user, Periodical periodical, int amount) throws DbException {
        BigDecimal accountBalance = user.getAccount().subtract(periodical.getPrice().multiply(new BigDecimal(amount)));
        if (!user.isBlocked() && (accountBalance.compareTo(new BigDecimal(0)) >= 0)) {
            subscribeDAO.insertSubscribe(user,periodical,amount);
                user.setAccount(accountBalance);
                userDAO.updateUser(user);
        }
    }

    @Override
    public void deleteSubscribeById(Subscribe subscribe)  throws DbException{
        subscribeDAO.deleteSubscribeById(subscribe);
    }

    @Override
    public void updateSubscribe(Subscribe subscribe)  throws DbException{
        subscribeDAO.updateSubscribe(subscribe);
    }

    @Override
    public List<Subscribe> getSubscribesByUser(User user)  throws DbException{
        List<Subscribe> activeSubscribes = new ArrayList<>();

        List<Subscribe> subscribes = subscribeDAO.getSubscribesByUser(user);
        for (Subscribe s: subscribes){
            if(isActive(s)) {
                activeSubscribes.add(s);
            } else {
                subscribeDAO.deleteSubscribeById(s);
            }
        }
        return activeSubscribes;
    }

    @Override
    public List<Subscribe> getSubscribesByPeriodical(Periodical periodical)  throws DbException {
        return subscribeDAO.getSubscribesByPeriodical(periodical);
    }

    @Override
    public Subscribe getSubscribeByID(Subscribe subscribe)  throws DbException{
        return subscribeDAO.getSubscribeByID(subscribe);
    }

    @Override
    public List<Subscribe> getActiveSubscribesByPeriodical(Periodical periodical) throws DbException {
        List<Subscribe> activeSubscribes = new ArrayList<>();

        List<Subscribe> subscribes = subscribeDAO.getSubscribesByPeriodical(periodical);
        for (Subscribe s: subscribes){
            if(isActive(s)) {
                activeSubscribes.add(s);
            }
        }
        return activeSubscribes;
    }

    /**
     * The method is used to check if a subscription is active
     * true - active
     * false - dis-active
     */
    @Override
    public boolean isActive(Subscribe subscribe) throws DbException {
        int currentIssue = periodicalDAO.getPeriodicalById(subscribe.getPeriodicalID()).getIssue();
        int finalIssue = subscribe.getFinalIssue();
        return currentIssue < finalIssue;
    }
}
