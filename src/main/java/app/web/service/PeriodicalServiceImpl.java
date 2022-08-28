package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.PeriodicalDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.Topic;
import app.web.service.interfacas.PeriodicalService;

import java.util.List;
import java.util.Map;

public class PeriodicalServiceImpl implements PeriodicalService {

    private final PeriodicalDAO periodicalDAO;

    public PeriodicalServiceImpl(PeriodicalDAO periodicalDAO) {
        this.periodicalDAO = periodicalDAO;
    }

    @Override
    public void insertPeriodical(Periodical periodical) throws DbException {
        periodicalDAO.insertPeriodical(periodical);
    }

    @Override
    public void updatePeriodical(Periodical periodical) throws DbException{
        periodicalDAO.updatePeriodical(periodical);
    }

    @Override
    public void deletePeriodical(int id) throws DbException{
        periodicalDAO.deletePeriodical(id);
    }

    @Override
    public Periodical getPeriodicalById(int id)  throws DbException{
        return periodicalDAO.getPeriodicalById(id);
    }

    @Override
    public Periodical getPeriodicalByUkrTitle(String titleUkr) throws DbException{
        return periodicalDAO.getPeriodicalByUkrTitle(titleUkr);
    }

    @Override
    public Periodical getPeriodicalByEngTitle(String titleEng) throws DbException{
        return periodicalDAO.getPeriodicalByEngTitle(titleEng);
    }

    @Override
    public List<Periodical> getPeriodicalsByTopic(Topic topic) throws DbException{
        return periodicalDAO.getPeriodicalsByTopic(topic);
    }

    public void setTopicsForNewPeriodicalTransaction(Periodical periodical, Topic...topics) throws DbException{
        periodicalDAO.setTopicForNewPeriodicalTransaction(periodical, topics);
    }

    @Override
    public List<Periodical> getAllPeriodicalsByEngTitle(int page) throws DbException{
        int offset = page*10-10;
        return periodicalDAO.getAllPeriodicalsByEngTitle(offset);
    }

    @Override
    public List<Periodical> getAllPeriodicalsByUkrTitle(int page) throws DbException{
        int offset = page*10-10;
        return periodicalDAO.getAllPeriodicalsByUkrTitle(offset);
    }

    @Override
    public List<Periodical> getAllPeriodicalsByPrice(int page) throws DbException{
        int offset = page*10-10;
        return periodicalDAO.getAllPeriodicalsByPrice(offset);
    }

    @Override
    public Map<Topic, List<Periodical>> getAllPeriodicalsByTopics(List<Topic> topics)  throws DbException{
        return periodicalDAO.getAllPeriodicalsByTopics(topics);
    }

    @Override
    public List<Periodical> getAllPeriodicalsBySubscribes(List<Subscribe> subscribes) throws DbException{
        return periodicalDAO.getAllPeriodicalsBySubscribes(subscribes);
    }

    @Override
    public List<Periodical> getAllPeriodicals() throws DbException {
        return periodicalDAO.getAllPeriodicals();
    }
}
