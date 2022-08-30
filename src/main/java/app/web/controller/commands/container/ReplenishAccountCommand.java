package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.User;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * The class represents the command to replenish account.
 */
public class ReplenishAccountCommand implements Command {
    private final UserService userService;

    /**
     * The constructor used to initialize services.
     *
     * @param userService          {@see app.web.service.UserServiceImpl}
     */
    public ReplenishAccountCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.replenish(new BigDecimal(req.getParameter("sum")));
        userService.updateUser(user);
        session.removeAttribute("user");
        session.setAttribute("user", user);
        return "app?command=personalAccount&wrong=-1";
    }
}
