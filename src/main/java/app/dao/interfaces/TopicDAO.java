package app.dao.interfaces;

import app.dao.DbException;
import app.entity.Topic;
import java.util.List;

public interface TopicDAO {

    boolean insertTopic(Topic topic) throws DbException;

    boolean deleteTopic(Topic topic) throws DbException;

    boolean updateTopic(Topic topic) throws DbException;

    Topic getTopicById(int id) throws DbException;

    List<Topic> getAllTopics() throws DbException;
}
