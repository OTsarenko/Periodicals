package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class represents the command to select all users.
 */
public class AllUsersCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AllUsersCommand.class);

    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     */
    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (DbException e) {
            LOGGER.error("Cant get users"+e);
            throw new CommandException(e);
        }
        req.setAttribute("users", users);
        return "/WEB-INF/view/all_users.jsp";
    }
}
