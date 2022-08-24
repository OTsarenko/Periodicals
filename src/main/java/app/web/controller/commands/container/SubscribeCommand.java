package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.User;
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

public class SubscribeCommand implements Command {

    private final SubscribeService subscribeService;

    private final UserService userService;

    private final PeriodicalService periodicalService;

    public SubscribeCommand(SubscribeService subscribeService, UserService userService, PeriodicalService periodicalService) {
        this.subscribeService = subscribeService;
        this.userService = userService;
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession s = req.getSession();
        User user = (User) s.getAttribute("user");
        Periodical periodical = periodicalService.getPeriodicalById(Integer.parseInt(req.getParameter("id")));
        int amount = Integer.parseInt(req.getParameter("amount"));
        subscribeService.insertSubscribe(user, periodical, amount);
        return "app?command=personalAccount";
    }
}
