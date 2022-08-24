package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnblockingUserCommand implements Command {

    private final UserService userService;

    public UnblockingUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        User user = userService.getUserById(Integer.parseInt(req.getParameter("id")));
        user.unblock();
        userService.updateUser(user);

        return "app?command=allUsers";
    }
}
