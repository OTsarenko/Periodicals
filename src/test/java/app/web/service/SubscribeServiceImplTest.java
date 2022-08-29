package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.SubscribeDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;
import app.web.service.SubscribeServiceImpl;
import app.web.service.interfacas.SubscribeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubscribeServiceImplTest {

    SubscribeDAO mockSubscribeDao = new SubscribeDAO() {
        @Override
        public boolean insertSubscribe(User user, Periodical periodical, int amount) throws DbException {
            return false;
        }

        @Override
        public boolean deleteSubscribeById(Subscribe subscribe) throws DbException {
            return false;
        }

        @Override
        public boolean updateSubscribe(Subscribe subscribe) throws DbException {
            return false;
        }

        @Override
        public List<Subscribe> getSubscribesByUser(User user) throws DbException {
            return null;
        }

        @Override
        public List<Subscribe> getSubscribesByPeriodical(Periodical periodical) throws DbException {
            return null;
        }

        @Override
        public Subscribe getSubscribeByID(Subscribe subscribe) throws DbException {
            return null;
        }
    };

    SubscribeService subscribeService = new SubscribeServiceImpl(mockSubscribeDao, null, null);

    @Test
    void deleteSubscribeById() {
        try {
            subscribeService.deleteSubscribeById(new Subscribe());
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }

    @Test
    void updateSubscribe() {
        try {
            subscribeService.updateSubscribe(new Subscribe());
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }

    @Test
    void getSubscribes() {
        try {
            assertEquals(null, subscribeService.getSubscribesByPeriodical(new Periodical()));
        } catch (DbException e) {
            fail("Cannot get subscribes", e);
        }
    }

    @Test
    void getSubscribeByID() {
        try {
            assertEquals(null, subscribeService.getSubscribeByID(new Subscribe()));
        } catch (DbException e) {
            fail("Cannot get subscribe", e);
        }
    }
}
