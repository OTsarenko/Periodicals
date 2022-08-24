package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class AllPeriodicalsByPriceCommand implements Command {
    private final PeriodicalService periodicalService;
    public AllPeriodicalsByPriceCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int page;
        if (req.getParameter("page") == null) {
            page = 1;
        } else{
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Periodical> periodicals;
        try {
            periodicals = periodicalService.getAllPeriodicalsByPrice(page);
        } catch (DbException e) {
            throw new CommandException(e);
        }
        req.removeAttribute("periodicals");
        req.setAttribute("periodicals", periodicals);
        List<Integer> pages = Utility.countPages(periodicalService.getAllPeriodicals());
        req.removeAttribute("pages");
        req.setAttribute("pages", pages);
        return "/WEB-INF/view/all_periodicals.jsp";
    }
}
