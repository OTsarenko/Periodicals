package app.web.controller.commands.container;

import app.dao.DbException;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePeriodicalCommand implements Command {

    private final PeriodicalService periodicalService;

    public DeletePeriodicalCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int id = Integer.parseInt(req.getParameter("id"));
        periodicalService.deletePeriodical(id);
        return "app?command=periodicalsForAdmin";
    }
}
