package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.enums.UserLanguage;
import app.util.EncryptPassword;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static app.util.Utility.validateData;

/**
 * The class represents the command to edit user.
 */
public class EditUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);

    private final UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String login = null;
        String email = null;
        String password = null;
        String message = null;
        String language = null;
        try {
            login = validateData(req.getParameter("login"));
            email = validateData(req.getParameter("email"));
            password = validateData(req.getParameter("password"));
            message = validateData(req.getParameter("message"));
            language = validateData(req.getParameter("language"));
        } catch (IOException e) {
            LOGGER.error("Data is not valid: ", e);
            throw new CommandException(e);
        }
        User checkUserLogin = userService.getUserByLogin(login);
        if (checkUserLogin == null) {
            user.setLogin(login);
        } else {
            LOGGER.info("User already exists in database: " + login);
            return "app?command=personalAccount&wrong=1";
        }

        User checkUserEmail = userService.getUserByMail(email);
        if (checkUserEmail == null) {
            user.setEmail(email);
        } else {
            LOGGER.info("User already exists in database: " + email);
            return "app?command=personalAccount&wrong=2";
        }

        user.setPassword(EncryptPassword.getSaltedHash(password));
        user.setMessage(message);
        user.setLanguage(UserLanguage.valueOf(language));
        userService.updateUser(user);
        session.removeAttribute("user");
        session.setAttribute("user", user);

        return "app?command=personalAccount&wrong=0";
    }
}
