package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Subscribe;
import app.entity.User;
import app.enums.UserRole;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.SubscribeService;
import app.web.service.interfacas.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PersonalAccountCommand implements Command {

    private final SubscribeService subscribeService;

    private final UserService userService;

    private final PeriodicalService periodicalService;

    public PersonalAccountCommand(SubscribeService subscribeService, UserService userService, PeriodicalService periodicalService) {
        this.subscribeService = subscribeService;
        this.userService = userService;
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        List<Subscribe> subscribes = subscribeService.getSubscribesByUser(user);
        List<Periodical> periodicalList = periodicalService.getAllPeriodicalsBySubscribes(subscribes);
        session.removeAttribute("periodicals");
        session.setAttribute("periodicals", periodicalList);
        return  (user.getUserRole() == UserRole.ADMINISTRATOR)?"/WEB-INF/view/admin_account.jsp": "/WEB-INF/view/personal_account.jsp";
    }
}
