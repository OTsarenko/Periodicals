package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.SubscribeDAO;
import app.dao.interfaces.UserDAO;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;
import app.web.service.interfacas.UserService;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final SubscribeDAO subscribeDAO;

    public UserServiceImpl(UserDAO userDAO, SubscribeDAO subscribeDAO) {
        this.userDAO = userDAO;
        this.subscribeDAO = subscribeDAO;
    }

    @Override
    public void insertUser(User user) throws DbException {
        userDAO.insertUser(user);
    }

    @Override
    public void deleteUserById(User user)  throws DbException{
        userDAO.deleteUserById(user);
    }

    @Override
    public void updateUser(User user)  throws DbException{
        userDAO.updateUser(user);
    }

    @Override
    public User getUserById(int id)  throws DbException{
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login)  throws DbException{
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User getUserByMail(String mail)  throws DbException{
        return userDAO.getUserByMail(mail);
    }

    @Override
    public List<User> findAllUsers()  throws DbException{
        return userDAO.findAllUsers();
    }

    @Override
    public List<User> findUsersOfSubscribe(Periodical periodical)  throws DbException {
        List<User> users = new ArrayList<>();
        List<Subscribe> subscribeList = subscribeDAO.getSubscribesByPeriodical(periodical);
        List<User> allUsers = userDAO.findAllUsers();

            for (Subscribe s: subscribeList) {
                for (User u : allUsers) {
                    if (s.getUserID() == u.getId()) users.add(u);
                }
            }
        return users;
    }
}
