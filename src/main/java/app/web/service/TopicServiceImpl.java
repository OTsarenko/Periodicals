package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.TopicDAO;
import app.entity.Topic;
import app.web.service.interfacas.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private final TopicDAO topicDAO;

    public TopicServiceImpl(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }

    @Override
    public void insertTopic(Topic topic)  throws DbException {
        topicDAO.insertTopic(topic);
    }

    @Override
    public void deleteTopic(Topic topic)  throws DbException{
        topicDAO.deleteTopic(topic);
    }

    @Override
    public void updateTopic(Topic topic)  throws DbException {
        topicDAO.updateTopic(topic);
    }

    @Override
    public Topic getTopicById(int id) throws DbException {
        return topicDAO.getTopicById(id);
    }

    @Override
    public List<Topic> getAllTopics()  throws DbException{
        return topicDAO.getAllTopics();
    }
}
