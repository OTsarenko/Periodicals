package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.Topic;
import app.web.service.PeriodicalServiceImpl;
import app.web.service.interfacas.PeriodicalService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PeriodicalServiceImplTest {

    PeriodicalDAO mockPeriodicalDao = new PeriodicalDAO() {
        @Override
        public boolean insertPeriodical(Periodical periodical) throws DbException {
            return false;
        }

        @Override
        public boolean updateIssue(Periodical periodical) throws DbException {
            return false;
        }

        @Override
        public boolean updatePeriodical(Periodical periodical) throws DbException {
            return false;
        }

        @Override
        public boolean deletePeriodical(int id) throws DbException {
            return false;
        }

        @Override
        public Periodical getPeriodicalById(int id) throws DbException {
            return null;
        }

        @Override
        public Periodical getPeriodicalByUkrTitle(String titleUkr) throws DbException {
            return null;
        }

        @Override
        public Periodical getPeriodicalByEngTitle(String titleEng) throws DbException {
            return null;
        }

        @Override
        public List<Periodical> getPeriodicalsByTopic(Topic topic) throws DbException {
            return null;
        }

        @Override
        public boolean setTopicForNewPeriodicalTransaction(Periodical periodical, Topic... topics) throws DbException {
            return false;
        }

        @Override
        public List<Periodical> getAllPeriodicalsByEngTitle(int offset) throws DbException {
            return null;
        }

        @Override
        public List<Periodical> getAllPeriodicalsByUkrTitle(int offset) throws DbException {
            return null;
        }

        @Override
        public List<Periodical> getAllPeriodicalsByPrice(int offset) throws DbException {
            return null;
        }

        @Override
        public Map<Topic, List<Periodical>> getAllPeriodicalsByTopics(List<Topic> topics) throws DbException {
            return null;
        }

        @Override
        public List<Periodical> getAllPeriodicalsBySubscribes(List<Subscribe> subscribes) throws DbException {
            return null;
        }

        @Override
        public List<Periodical> getAllPeriodicals() throws DbException {
            return null;
        }
    };

    PeriodicalService periodicalService = new PeriodicalServiceImpl(mockPeriodicalDao);

    @Test
    void insertPeriodical() {
        try {
            periodicalService.insertPeriodical(new Periodical());
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }

    @Test
    void updatePeriodical(){
        try {
            periodicalService.updatePeriodical(new Periodical());
        } catch (DbException e) {
            fail("Cannot update");
        }
    }

    @Test
    void deletePeriodical(){
        try {
            periodicalService.deletePeriodical(1);
        } catch (DbException e) {
            fail("Cannot delete");
        }
    }

    @Test
    void getPeriodical(){
        try {
            assertEquals(null, periodicalService.getPeriodicalById(1));
            assertEquals(null, periodicalService.getPeriodicalByEngTitle("testTitle"));
            assertEquals(null, periodicalService.getPeriodicalByUkrTitle("тестоваНазва"));
        } catch (DbException e) {
            fail("Cannot get periodical", e);
        }
    }

    @Test
    void setTopicsForNewPeriodicalTransaction(){
        try {
            periodicalService.setTopicsForNewPeriodicalTransaction(new Periodical(), new Topic[]{});
        } catch (DbException e) {
            fail("Cannot set", e);
        }
    }

    @Test
    void getAllPeriodicals(){
        try {
            assertEquals(null, periodicalService.getAllPeriodicals());
            assertEquals(null, periodicalService.getAllPeriodicalsByEngTitle(1));
            assertEquals(null, periodicalService.getAllPeriodicalsByUkrTitle(1));
            assertEquals(null, periodicalService.getAllPeriodicalsByPrice(1));
            assertEquals(null, periodicalService.getAllPeriodicalsBySubscribes(new ArrayList<>()));
            assertEquals(null, periodicalService.getAllPeriodicalsByTopics(new ArrayList<>()));
        } catch (DbException e) {
            fail("Cannot get periodicals", e);
        }
    }
}
