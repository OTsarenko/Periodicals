package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.UserDAO;
import app.entity.User;
import app.web.service.interfacas.UserService;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UserServiceImplTest {

    UserDAO mockUserDao = new UserDAO() {
        @Override
        public boolean insertUser(User user) throws DbException {
            return true;
        }

        @Override
        public boolean deleteUserById(User user) throws DbException {
            return true;
        }

        @Override
        public boolean updateUser(User user) throws DbException {
            return true;
        }

        @Override
        public User getUserById(int id) throws DbException {
            return null;
        }

        @Override
        public User getUserByLogin(String login) throws DbException {
            return null;
        }

        @Override
        public User getUserByMail(String mail) throws DbException {
            return null;
        }

        @Override
        public List<User> findAllUsers() throws DbException {
            return null;
        }
    };

    UserService userService = new UserServiceImpl(mockUserDao);

    @Test
    void insertUser(){
        try {
            userService.insertUser(new User());
        } catch (DbException e) {
            fail("Cannot insert");
        }
    }

    @Test
    void deleteUserById(){
        try {
            userService.deleteUserById(new User());
        } catch (DbException e) {
            fail("Cannot delete");
        }
    }

    @Test
    void updateUser(){
        try {
            userService.updateUser(new User());
        } catch (DbException e) {
            fail("Cannot update");
        }
    }

    @Test
    void getUser(){
        try {
            assertEquals(null, userService.getUserById(1));
            assertEquals(null, userService.getUserByLogin("testLogin"));
            assertEquals(null, userService.getUserByMail("testMail@ukr.net"));
        } catch (DbException e) {
            fail("Cannot get user");
        }
    }

    @Test
    void findAllUsers(){
        try {
            assertEquals(null, userService.findAllUsers());
        } catch (DbException e) {
            fail("Cannot get user");
        }
    }
}
