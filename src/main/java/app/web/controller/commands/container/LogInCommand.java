package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.util.EncryptPassword;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The class represents the command to log in user.
 */
public class LogInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     */
    public LogInCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        String login = null;
        String password = null;
        try {
            login = Utility.validateData(req.getParameter("login"));
            password = Utility.validateData(req.getParameter("password"));
        } catch (IOException e) {
            LOGGER.info("Data is not valid");
            throw new CommandException(e);
        }
        User user = userService.getUserByLogin(login);
        if(user != null && EncryptPassword.check(password, user.getPassword()) && !user.isBlocked()) {
            session.setAttribute("user", user);
        } else {
            LOGGER.info("Cannot log in");
            return "app?command=logInForm&wrong=1";
        }
        return "app?command=personalAccount";
    }
}
