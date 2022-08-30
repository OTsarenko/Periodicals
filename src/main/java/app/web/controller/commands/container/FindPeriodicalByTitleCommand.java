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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the command to find periodical.
 */
public class FindPeriodicalByTitleCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(FindPeriodicalByTitleCommand.class);

    private final PeriodicalService periodicalService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     */
    public FindPeriodicalByTitleCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {

        List<Periodical> periodicals = new ArrayList<>();
        String title = null;
        try {
            title = Utility.validateData(req.getParameter("title"));
        } catch (IOException e) {
            LOGGER.error("Cannot get parameter"+e);
            try {
                resp.sendError(400, "Data is invalid");
            } catch (IOException ex) {
                throw new CommandException(ex);
            }
        }
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
