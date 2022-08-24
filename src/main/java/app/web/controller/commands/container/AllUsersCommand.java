package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.User;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllUsersCommand implements Command {

    private final UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        List<User> users = null;
        try {
            users = userService.findAllUsers();
        } catch (DbException e) {
            throw new CommandException(e);
        }
        req.setAttribute("users", users);
        return "/WEB-INF/view/all_users.jsp";
    }
}
