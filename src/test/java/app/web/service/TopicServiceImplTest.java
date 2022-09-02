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
        MyAssertions.assertDoesNotThrow(() -> {topicService.insertTopic(new Topic());});
    }

    @Test
    void delete() {
            MyAssertions.assertDoesNotThrow(() -> {topicService.deleteTopic(new Topic());});
    }

    @Test
    void update() {
            MyAssertions.assertDoesNotThrow(() -> {topicService.updateTopic(new Topic());});
    }

    @Test
    void getTopic() {
        try {
            assertNull(topicService.getTopicById(1));
            assertNull(topicService.getTopicById(2));
            assertNull(topicService.getTopicById(3));
        } catch (DbException e) {
            fail("Cannot get topic", e);
        }
    }

    @Test
    void getAllTopics() {
        try {
            assertNull(topicService.getAllTopics());
        } catch (DbException e) {
            fail("Cannot get all topics");
        }
    }
}
