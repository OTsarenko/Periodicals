package app.web.service;

import app.dao.DbException;
import app.dao.interfaces.UserDAO;
import app.entity.User;
import app.web.service.interfacas.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void insertUser() {
        MyAssertions.assertDoesNotThrow(() -> userService.insertUser(new User()));
    }

    @Test
    void deleteUserById() {
        MyAssertions.assertDoesNotThrow(() -> userService.deleteUserById(new User()));
    }

    @Test
    void updateUser() {
        MyAssertions.assertDoesNotThrow(() -> userService.updateUser(new User()));
    }

    @Test
    void getUser() {
        try {
            assertNull(userService.getUserById(1));
            assertNull(userService.getUserById(2));
            assertNull(userService.getUserByLogin("testLogin"));
            assertNull(userService.getUserByLogin("testLogin2"));
            assertNull(userService.getUserByMail("testMail@ukr.net"));
            assertNull(userService.getUserByMail("test2Mail@ukr.net"));
        } catch (DbException e) {
            fail("Cannot get user");
        }
    }

    @Test
    void findAllUsers() {
        try {
            assertNull(userService.findAllUsers());
        } catch (DbException e) {
            fail("Cannot get user");
        }
    }
}
