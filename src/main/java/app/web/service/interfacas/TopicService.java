package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Topic;

import java.util.List;

public interface TopicService {


    void insertTopic(Topic topic)  throws DbException;

    void deleteTopic(Topic topic)  throws DbException;

    void updateTopic(Topic topic)  throws DbException;

    Topic getTopicById (int id) throws DbException;

    List<Topic> getAllTopics()  throws DbException;

}
