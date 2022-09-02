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

import static org.junit.jupiter.api.Assertions.*;

class PeriodicalServiceImplTest {

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
        MyAssertions.assertDoesNotThrow(() -> periodicalService.insertPeriodical(new Periodical()));
    }

    @Test
    void updatePeriodical(){
            MyAssertions.assertDoesNotThrow(() -> periodicalService.updatePeriodical(new Periodical()));
    }

    @Test
    void deletePeriodical(){
        MyAssertions.assertDoesNotThrow(() -> periodicalService.deletePeriodical(1));
        MyAssertions.assertDoesNotThrow(() -> periodicalService.deletePeriodical(2));
    }

    @Test
    void getPeriodical(){
        try {
            assertNull(periodicalService.getPeriodicalById(1));
            assertNull(periodicalService.getPeriodicalById(2));
            assertNull(periodicalService.getPeriodicalByEngTitle("testTitle"));
            assertNull(periodicalService.getPeriodicalByEngTitle("test2Title"));
            assertNull(periodicalService.getPeriodicalByUkrTitle("тестоваНазва"));
            assertNull(periodicalService.getPeriodicalByUkrTitle("тестоваНазва2"));
        } catch (DbException e) {
            fail("Cannot get periodical", e);
        }
    }

    @Test
    void setTopicsForNewPeriodicalTransaction(){
        MyAssertions.assertDoesNotThrow(() -> periodicalService.setTopicsForNewPeriodicalTransaction(new Periodical(), new Topic[]{}));
    }

    @Test
    void getAllPeriodicals(){
        try {
            assertNull(periodicalService.getAllPeriodicals());
            assertNull(periodicalService.getAllPeriodicalsByEngTitle(1));
            assertNull(periodicalService.getAllPeriodicalsByUkrTitle(1));
            assertNull(periodicalService.getAllPeriodicalsByPrice(1));
            assertNull(periodicalService.getAllPeriodicalsBySubscribes(new ArrayList<>()));
            assertNull(periodicalService.getAllPeriodicalsByTopics(new ArrayList<>()));
        } catch (DbException e) {
            fail("Cannot get periodicals", e);
        }
    }
}
