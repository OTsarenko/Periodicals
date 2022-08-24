package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Subscribe;
import app.entity.User;
import app.util.EncryptPassword;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.SubscribeService;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LogInCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogInCommand.class);

    private final UserService userService;

    public LogInCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
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
