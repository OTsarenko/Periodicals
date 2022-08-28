package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class represents the command to select all periodicals by price.
 */
public class AllPeriodicalsByPriceCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AllPeriodicalsByPriceCommand.class);

    private final PeriodicalService periodicalService;

    public AllPeriodicalsByPriceCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Periodical> periodicals = null;
        try {
            periodicals = periodicalService.getAllPeriodicalsByPrice(page);
        } catch (DbException e) {
            LOGGER.error("Cant get periodicals"+e);
            throw new CommandException(e);
        }
        req.removeAttribute("periodicals");
        req.setAttribute("periodicals", periodicals);
        List<Integer> pages = Utility.countPages(periodicalService.getAllPeriodicals());
        req.setAttribute("pages", pages);
        return "/WEB-INF/view/all_periodicals.jsp";
    }
}
