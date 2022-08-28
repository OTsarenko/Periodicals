package app.dao.interfaces;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.Topic;
import java.util.List;
import java.util.Map;

public interface PeriodicalDAO {

    boolean insertPeriodical(Periodical periodical) throws DbException;

    boolean updateIssue (Periodical periodical) throws DbException;

    boolean updatePeriodical(Periodical periodical) throws DbException;

    boolean deletePeriodical(int id) throws DbException;

    Periodical getPeriodicalById(int id)  throws DbException;

    Periodical getPeriodicalByUkrTitle(String titleUkr) throws DbException;

    Periodical getPeriodicalByEngTitle(String titleEng) throws DbException;

    List<Periodical> getPeriodicalsByTopic(Topic topic) throws DbException;

    boolean setTopicForNewPeriodicalTransaction(Periodical periodical, Topic...topics) throws DbException;
    List<Periodical> getAllPeriodicalsByEngTitle(int offset) throws DbException;
    List<Periodical> getAllPeriodicalsByUkrTitle(int offset) throws DbException;

    List<Periodical> getAllPeriodicalsByPrice(int offset) throws DbException;

    Map<Topic, List<Periodical>> getAllPeriodicalsByTopics(List<Topic> topics)  throws DbException;

    List<Periodical> getAllPeriodicalsBySubscribes(List<Subscribe> subscribes) throws DbException;

    List<Periodical> getAllPeriodicals() throws DbException;
}
