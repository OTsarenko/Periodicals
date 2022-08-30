package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.enums.UserLanguage;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class represents the command to set locale.
 */
public class SetLocaleCommand implements Command {

    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     */
    public SetLocaleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        String locale = req.getParameter("locale");
        session.setAttribute("locale", locale);
        User user = (User) session.getAttribute("user");
        if (locale == null && user != null) {
            user.setLanguage(UserLanguage.ENG);
            userService.updateUser(user);
        }
        return "app?command="+req.getParameter("com");
    }
}
