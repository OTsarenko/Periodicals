package app.dao.mysql;

import app.dao.DbException;
import app.dao.interfaces.TopicDAO;
import app.entity.Topic;
import app.util.ConnectionDataSource;
import app.util.ConstantsQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class, that represents topics DAO for MySQL.
 */
public class TopicDAOImpl implements TopicDAO {

    private static final Logger LOGGER = LogManager.getLogger(TopicDAOImpl.class);

    public Connection getConnection() throws SQLException {
        return ConnectionDataSource.getInstance().getConnection();
    }

    @Override
    public boolean insertTopic(Topic topic)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_TOPIC)) {
            setTopicParameters(topic, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't insert topic:", e);
            throw new DbException(e);
        }
    }

    @Override
    public boolean deleteTopic(Topic topic)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_TOPIC)){
            preparedStatement.setInt(1, topic.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't delete topic:", e);
            throw new DbException(e);
        }
    }

    @Override
    public boolean updateTopic(Topic topic)  throws DbException {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_TOPIC)){
            setTopicParameters(topic, preparedStatement);
            preparedStatement.setInt(3, topic.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't update topic:", e);
            throw new DbException(e);
        }
    }

    @Override
    public Topic getTopicById(int id) throws DbException {

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOPIC_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Topic topic = new Topic();
                topic.setId(resultSet.getInt("id"));
                topic.setEngTopicName(resultSet.getString("name_eng"));
                topic.setUkrTopicName(resultSet.getString("name_ukr"));
                return topic;
            }

        } catch (SQLException e) {
            LOGGER.error("Can't get topic by id:", e);
            throw new DbException(e);
        }
        return null;
    }


    public Topic getTopicByEngTitle(String engTitle) throws DbException {

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_TOPIC_BY_ENG_NAME)) {
            preparedStatement.setString(1, engTitle);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Topic topic = new Topic();
                topic.setId(resultSet.getInt("id"));
                topic.setEngTopicName(resultSet.getString("name_eng"));
                topic.setUkrTopicName(resultSet.getString("name_ukr"));
                return topic;
            }

        } catch (SQLException e) {
            LOGGER.error("Can't get topic by id:", e);
            throw new DbException(e);
        }
        return null;
    }

    @Override
    public List<Topic> getAllTopics()  throws DbException{
        List<Topic> topics = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_TOPICS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setEngTopicName(rs.getString("name_eng"));
                topic.setUkrTopicName(rs.getString("name_ukr"));
                topics.add(topic);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find all topics:", e);
            throw new DbException(e);
        }
        return topics;
    }

    private void setTopicParameters(Topic topic, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, topic.getEngTopicName());
        preparedStatement.setString(2, topic.getUkrTopicName());
    }
}
