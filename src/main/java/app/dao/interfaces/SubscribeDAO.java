package app.dao.interfaces;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;
import java.util.List;

public interface SubscribeDAO {

    boolean insertSubscribe(User user, Periodical periodical, int amount) throws DbException;

    boolean deleteSubscribeById(Subscribe subscribe) throws DbException;

    boolean updateSubscribe(Subscribe subscribe) throws DbException;

    List<Subscribe> getSubscribesByUser(User user) throws DbException;

    List<Subscribe> getSubscribesByPeriodical(Periodical periodical) throws DbException;

    Subscribe getSubscribeByID(Subscribe subscribe) throws DbException;
}
