package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.User;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.SubscribeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class represents the command to edit user.
 */
public class SubscribeCommand implements Command {

    private final SubscribeService subscribeService;

    private final PeriodicalService periodicalService;

    public SubscribeCommand(SubscribeService subscribeService,  PeriodicalService periodicalService) {
        this.subscribeService = subscribeService;
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
