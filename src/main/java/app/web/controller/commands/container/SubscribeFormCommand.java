package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class represents the command to go to subscribe form.
 */
public class SubscribeFormCommand implements Command {

    private final PeriodicalService periodicalService;

    public SubscribeFormCommand( PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        Periodical periodical = periodicalService.getPeriodicalById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("periodical", periodical);
        return "/WEB-INF/view/subscribe_form.jsp";
    }
}
