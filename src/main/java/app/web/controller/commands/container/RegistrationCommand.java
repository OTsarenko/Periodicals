package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Subscribe;
import app.entity.User;
import app.util.EncryptPassword;
import app.util.Utility;
import app.web.controller.Controller;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.SubscribeService;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    private final SubscribeService subscribeService;

    private final UserService userService;

    public RegistrationCommand(SubscribeService subscribeService, UserService userService) {
        this.subscribeService = subscribeService;
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");

        User checkUserLogin = userService.getUserByLogin(login);
        User checkUserEmail = userService.getUserByMail(email);

        if (checkUserLogin == null && checkUserEmail == null) {
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(EncryptPassword.getSaltedHash(req.getParameter("password")));
            userService.insertUser(user);
            User newUser = userService.getUserByLogin(login);
            HttpSession session = req.getSession();
            List<Subscribe> subscribeList = subscribeService.getSubscribesByUser(newUser);
            session.setAttribute("user", newUser);
            session.setAttribute("subscribes", subscribeList);
        } else {
            LOGGER.info("User already exists in database: "+ login+ " " + email);
            if (checkUserLogin != null && checkUserEmail != null) return "app?command=registrationForm&wrong=1";
            if ( checkUserLogin != null) return "app?command=registrationForm&wrong=2";
            if ( checkUserEmail != null) return "app?command=registrationForm&wrong=3";
        }
        return "app?command=personalAccount";
    }
}
