package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditPeriodicalFormCommand implements Command {

    private final PeriodicalService periodicalService;

    public EditPeriodicalFormCommand( PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int id = Integer.parseInt(req.getParameter("id"));
        Periodical periodical = periodicalService.getPeriodicalById(id);
        req.setAttribute("periodical", periodical);
        return "/WEB-INF/view/edit_periodical_form.jsp";
    }
}
