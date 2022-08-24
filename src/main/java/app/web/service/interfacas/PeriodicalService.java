package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.Topic;

import java.util.List;
import java.util.Map;

public interface PeriodicalService {

    void insertPeriodical(Periodical periodical) throws DbException;

    void updatePeriodical(Periodical periodical) throws DbException;

    void deletePeriodical(int id) throws DbException;

    Periodical getPeriodicalById(int id)  throws DbException;

    Periodical getPeriodicalByUkrTitle(String titleUkr) throws DbException;

    Periodical getPeriodicalByEngTitle(String titleEng) throws DbException;

    List<Periodical> getPeriodicalsByTopic(Topic topic) throws DbException;

    void setTopicForPeriodical(Topic topic, Periodical periodical) throws DbException;

    List<Periodical> getAllPeriodicalsByEngTitle(int page) throws DbException;

    List<Periodical> getAllPeriodicalsByUkrTitle(int page) throws DbException;

    List<Periodical> getAllPeriodicalsByPrice(int page) throws DbException;

    Map<Topic, List<Periodical>> getAllPeriodicalsByTopics(List<Topic> topics)  throws DbException;

    List<Periodical> getAllPeriodicalsBySubscribes(List<Subscribe> subscribes) throws DbException;

    List<Periodical> getAllPeriodicals() throws DbException;
}
