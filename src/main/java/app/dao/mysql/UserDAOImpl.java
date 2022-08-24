package app.dao.mysql;

import app.dao.DbException;
import app.dao.interfaces.UserDAO;
import app.entity.User;
import app.enums.UserLanguage;
import app.enums.UserRole;
import app.enums.UserStatus;
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
 * The class, that represents user DAO for MySQL.
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    Connection getConnection() {
        return ConnectionDataSource.getInstance().getConnection();
    }

    @Override
    public boolean insertUser(User user) throws DbException {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.INSERT_USER)) {
            setUserParameters(user, preparedStatement);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't insert user:", e);
        }
        return false;
    }

    @Override
    public boolean deleteUserById(User user)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, user.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Can't delete user:", e);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user)  throws DbException{
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.UPDATE_USER)){
            setUserParameters(user, preparedStatement);
            preparedStatement.setInt(9, user.getId());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error("Can't update user:", e);
        }
        return false;
    }

    @Override
    public User getUserById(int id)  throws DbException{

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserExecute(resultSet);
            }

        } catch (SQLException e) {
            LOGGER.error("Can't get user by id:", e);
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login)  throws DbException{

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserExecute(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't get user by login:", e);
        }
        return null;
    }

    @Override
    public User getUserByMail(String mail) throws DbException{

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserExecute(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can't get user by mail:", e);
        }
        return null;
    }

    @Override
    public List<User> findAllUsers()  throws DbException{
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(ConstantsQuery.FIND_ALL_USERS)) {
            return getUserListExecute(users, preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("Can't find all users:", e);
        }
        return users;
    }

    private void setUserParameters(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getFacebook());
        preparedStatement.setBigDecimal(5, user.getAccount());
        preparedStatement.setString(6, user.getStatus().name());
        preparedStatement.setString(7, user.getUserRole().name());
        preparedStatement.setString(8, user.getLanguage().name());
    }

    private User getUserExecute(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setFacebook(resultSet.getString("facebook"));
            user.setAccount(resultSet.getBigDecimal("account"));
            user.setStatus(UserStatus.valueOf(resultSet.getString("status")));
            user.setUserRole(UserRole.valueOf(resultSet.getString("r")));
            user.setLanguage(UserLanguage.valueOf(resultSet.getString("user_language")));
        return user;
    }

    private List<User> getUserListExecute(List<User> users, PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            User user = getUserExecute(rs);
            users.add(user);
        }
        return users;
    }
}
