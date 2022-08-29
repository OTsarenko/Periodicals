package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.TopicDAO;
import app.entity.Topic;
import app.web.service.TopicServiceImpl;
import app.web.service.interfacas.TopicService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TopicServiceImplTest {

    TopicDAO mockDao = new TopicDAO() {
        @Override
        public boolean insertTopic(Topic topic) throws DbException {
            return true;
        }

        @Override
        public boolean deleteTopic(Topic topic) throws DbException {
            return true;
        }

        @Override
        public boolean updateTopic(Topic topic) throws DbException {
            return true;
        }

        @Override
        public Topic getTopicById(int id) throws DbException {
            return null;
        }

        @Override
        public List<Topic> getAllTopics() throws DbException {
            return null;
        }
    };
    TopicService topicService = new TopicServiceImpl(mockDao);

    @Test
    void insert() {
        try {
            topicService.insertTopic(new Topic());
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }

    @Test
    void delete() {
        try {
            topicService.deleteTopic(new Topic());
        } catch (DbException e) {
            fail("Cannot delete");
        }
    }

    @Test
    void update() {
        try {
           topicService.updateTopic(new Topic());
        } catch (DbException e) {
            fail("Cannot update");
        }
    }

    @Test
    void getTopic() {
        try {
            assertEquals(null, topicService.getTopicById(1));
        } catch (DbException e) {
            fail("Cannot get topic", e);
        }
    }

    @Test
    void getAllTopics() {
        try {
            assertEquals(null, topicService.getAllTopics());
        } catch (DbException e) {
            fail("Cannot get all topics");
        }
    }
}
