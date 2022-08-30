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

/**
 * The class represents the command to block users.
 */
public class BlockingUserCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(BlockingUserCommand.class);

    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     */
    public BlockingUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        try {
            User user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
            user.block();
            userService.updateUser(user);
        } catch (DbException e) {
            LOGGER.error("Cant block user" + e);
            throw new CommandException(e);
        }
        return "app?command=allUsers";
    }
}
