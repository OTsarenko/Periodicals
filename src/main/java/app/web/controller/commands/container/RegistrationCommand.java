package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Subscribe;
import app.entity.User;
import app.util.EncryptPassword;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.SubscribeService;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static app.util.Utility.validateData;

/**
 * The class represents the command to registration.
 */
public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    private final SubscribeService subscribeService;

    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     * @param subscribeService     {@see app.web.service.SubscribeServiceImpl}
     */
    public RegistrationCommand(SubscribeService subscribeService, UserService userService) {
        this.subscribeService = subscribeService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        String login = null;
        String email = null;
        try {
            login = validateData(req.getParameter("login"));
            email = validateData(req.getParameter("email"));
        } catch (IOException e) {
            LOGGER.error("Data is not valid: " + login + " " + email);
            throw new CommandException(e);
        }
        User checkUserLogin = null;
        User checkUserEmail = null;
        try {
            checkUserLogin = userService.getUserByLogin(login);
            checkUserEmail = userService.getUserByMail(email);
        } catch (DbException e) {
            LOGGER.error("Cannot get user", e);
            throw new CommandException(e);
        }
        if (checkUserLogin == null && checkUserEmail == null) {
            User user = new User();
            user.setLogin(login);
            user.setEmail(email);
            user.setPassword(EncryptPassword.getSaltedHash(req.getParameter("password")));
            User newUser = null;
            try {
                userService.insertUser(user);
                newUser = userService.getUserByLogin(login);
            } catch (DbException e) {
                LOGGER.error("Cannot insert user", e);
                throw new CommandException(e);
            }
            HttpSession session = req.getSession();
            List<Subscribe> subscribeList = subscribeService.getSubscribesByUser(newUser);
            session.setAttribute("user", newUser);
            session.setAttribute("subscribes", subscribeList);
        } else {
            LOGGER.info("User already exists in database: " + login + " " + email);
            if (checkUserLogin != null && checkUserEmail != null) return "app?command=registrationForm&wrong=1";
            return (checkUserLogin != null)?"app?command=registrationForm&wrong=2":"app?command=registrationForm&wrong=3";
        }
        return "app?command=personalAccount";
    }
}
