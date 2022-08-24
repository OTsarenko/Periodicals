package app.dao.mysql;

import app.dao.DbException;
import app.dao.interfaces.SubscribeDAO;
import app.entity.User;
import app.entity.Periodical;
import app.entity.Subscribe;
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
 * The class, that represents subscribe DAO.
 */
public class SubscribeDAOImpl implements SubscribeDAO {

    private static final Logger LOGGER = LogManager.getLogger(SubscribeDAOImpl.class);

    Connection getConnection() {
        return ConnectionDataSource.getInstance().getConnection();
    }

    @Override
    public boolean insertSubscribe(User user, Periodical periodical, int amount)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_SUBSCRIBE)) {
            preparedStatement.setInt(1, periodical.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setInt(3, periodical.getIssue()+amount-1);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't insert subscribe:", e);
        }
        return false;
    }

    @Override
    public boolean deleteSubscribeById(Subscribe subscribe)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_SUBSCRIBE)) {
            preparedStatement.setInt(1, subscribe.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Can't delete subscribe:", e);
        }
        return false;
    }

    @Override
    public boolean updateSubscribe(Subscribe subscribe)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_SUBSCRIBE)){
            preparedStatement.setInt(2, subscribe.getFinalIssue());
            preparedStatement.setInt(3, subscribe.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't update subscribe:", e);
        }
        return false;
    }

    @Override
    public List<Subscribe> getSubscribesByUser(User user)  throws DbException{
        List<Subscribe> subscribes = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_SUBSCRIBES_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Subscribe s = getSubscribeExecute(resultSet);
                subscribes.add(s);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find subscribes:", e);
        }
        return subscribes;
    }

    @Override
    public List<Subscribe> getSubscribesByPeriodical(Periodical periodical)  throws DbException {
        List<Subscribe> subscribes = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_SUBSCRIBES_BY_PERIODICAL)) {
            preparedStatement.setInt(1, periodical.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Subscribe s = getSubscribeExecute(resultSet);
                subscribes.add(s);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find subscribes:", e);
        }
        return subscribes;
    }

    @Override
    public Subscribe getSubscribeByID(Subscribe subscribe)  throws DbException{

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_SUBSCRIBE_BY_ID)) {
            preparedStatement.setInt(1, subscribe.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return getSubscribeExecute(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't find subscribe:", e);
        }
        return null;
    }

    private Subscribe getSubscribeExecute (ResultSet resultSet) throws SQLException {
        Subscribe subscribe = new Subscribe();
        subscribe.setId(resultSet.getInt("id"));
        subscribe.setPeriodicalID(resultSet.getInt("periodical_id"));
        subscribe.setUserID(resultSet.getInt("user_id"));
        subscribe.setFinalIssue(resultSet.getInt("final_issue"));
        return subscribe;
    }
}
