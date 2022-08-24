package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Subscribe;
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
import java.util.List;

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
        String login = req.getParameter("login");
        if (login != null && !"".equals(login)) {
            User checkUserLogin = userService.getUserByLogin(login);
            if (checkUserLogin == null) {
                user.setLogin(login);
            } else {
                LOGGER.info("User already exists in database: " + login);
                return "app?command=personalAccount&wrong=1";
            }
        }
        String email = req.getParameter("email");
        if (email != null && !email.equals("")) {
            User checkUserEmail = userService.getUserByMail(email);
            if (checkUserEmail == null) {
                user.setEmail(email);
            } else {
                LOGGER.info("User already exists in database: " + email);
                return "app?command=personalAccount&wrong=2";
            }
        }
        String password = req.getParameter("password");
        if ( password != null && !password.equals("")){
            user.setPassword(EncryptPassword.getSaltedHash(req.getParameter("password")));
        }
        String facebook = req.getParameter("facebook");
        if (facebook != null && !"".equals(facebook)) user.setFacebook(facebook);
        String language = req.getParameter("language");
        if (language != null && !"".equals(language)) user.setLanguage(UserLanguage.valueOf(language));
        userService.updateUser(user);
        session.removeAttribute("user");
        session.setAttribute("user", user);

        return "app?command=personalAccount&wrong=0";
    }
}
