package app.dao.interfaces;

import app.dao.DbException;
import app.entity.User;

import java.util.List;

public interface UserDAO {

    boolean insertUser(User user) throws DbException;

    boolean deleteUserById(User user) throws DbException;

    boolean updateUser(User user) throws DbException;

    User getUserById(int id) throws DbException;

    User getUserByLogin(String login) throws DbException;

    User getUserByMail(String mail) throws DbException;

    List<User> findAllUsers() throws DbException;
}
