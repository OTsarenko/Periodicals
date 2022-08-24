package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FindPeriodicalByTitleCommand implements Command {

    private final PeriodicalService periodicalService;
    public FindPeriodicalByTitleCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {

        List<Periodical> periodicals = new ArrayList<>();
        String title = req.getParameter("title");
        Periodical p1 = periodicalService.getPeriodicalByEngTitle(title);
        Periodical p2 = periodicalService.getPeriodicalByUkrTitle(title);
        if (p1 != null) periodicals.add(p1);
        if (p2 != null) periodicals.add(p2);
        req.setAttribute("periodicals", periodicals);
        List<Integer> pages = Utility.countPages(periodicals);
        req.setAttribute("pages", pages);

        return "/WEB-INF/view/all_periodicals.jsp";
    }
}
