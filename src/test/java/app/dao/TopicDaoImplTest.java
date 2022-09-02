package app.dao;

import app.dao.mysql.TopicDAOImpl;
import app.entity.Topic;
import app.util.ConnectionDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TopicDaoImplTest {

    static TopicDAOImpl impl;
    static Topic topic;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception{
        impl = new TopicDAOImpl(DbUtil.getDataSource("jdbc:mysql://localhost/periodicals", "root", "6625").getConnection());
    }

    @Test
    void topicTest(){
        try {
            assertTrue( impl.insertTopic(new Topic("Beauty", "Краса")));
            assertNotNull(impl.getTopicByEngTitle("Beauty"));
            topic = impl.getTopicByEngTitle("Beauty");
            topic.setUkrTopicName("Краса краса");
            assertTrue( impl.updateTopic(topic));
            assertEquals(topic, impl.getTopicById(topic.getId()));
            assertTrue( impl.deleteTopic(topic));
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }
}
