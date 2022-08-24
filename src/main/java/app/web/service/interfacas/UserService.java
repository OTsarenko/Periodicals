package app.web.service.interfacas;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.User;

import java.util.List;

public interface UserService {

    void insertUser(User user) throws DbException;

    void deleteUserById(User user)  throws DbException;

    void updateUser(User user)  throws DbException;

    User getUserById(int id)  throws DbException;

    User getUserByLogin(String login)  throws DbException;

    User getUserByMail(String mail)  throws DbException;

    List<User> findAllUsers()  throws DbException;

    List<User> findUsersOfSubscribe(Periodical periodical)  throws DbException;
}
