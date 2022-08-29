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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The class represents the command to go to personal account.
 */
public class PersonalAccountCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PersonalAccountCommand.class);

    private final SubscribeService subscribeService;

    private final PeriodicalService periodicalService;

    public PersonalAccountCommand(SubscribeService subscribeService, PeriodicalService periodicalService) {
        this.subscribeService = subscribeService;
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Subscribe> subscribes = null;
        List<Periodical> periodicalList = null;
        try {
            subscribes = subscribeService.getSubscribesByUser(user);
            periodicalList = periodicalService.getAllPeriodicalsBySubscribes(subscribes);
        } catch (DbException e) {
            LOGGER.error("Cannot get: ",e);
            throw new CommandException(e);
        }
        session.removeAttribute("periodicals");
        session.setAttribute("periodicals", periodicalList);

        return  (user.getUserRole() == UserRole.ADMINISTRATOR)?"/WEB-INF/view/admin_account.jsp": "/WEB-INF/view/personal_account.jsp";
    }
}
