package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;

import java.util.List;

public interface SubscribeService {

    void insertSubscribe(User user, Periodical periodical, int amount) throws DbException;

    void deleteSubscribeById(Subscribe subscribe)  throws DbException;

    void updateSubscribe(Subscribe subscribe)  throws DbException;

    List<Subscribe> getSubscribesByUser(User user)  throws DbException;

    List<Subscribe> getSubscribesByPeriodical(Periodical periodical)  throws DbException;

    Subscribe getSubscribeByID(Subscribe subscribe)  throws DbException;

    List<Subscribe> getActiveSubscribesByPeriodical(Periodical periodical) throws DbException;

    boolean isActive(Subscribe subscribe) throws DbException;
}
