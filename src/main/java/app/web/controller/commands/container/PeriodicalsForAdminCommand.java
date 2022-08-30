package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Topic;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class represents the command to get periodicals for admin.
 */
public class PeriodicalsForAdminCommand implements Command {

    private final PeriodicalService periodicalService;
    private final TopicService topicService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     * @param topicService         {@see app.web.service.TopicServiceImpl}
     */
    public PeriodicalsForAdminCommand(PeriodicalService periodicalService, TopicService topicService) {
        this.periodicalService = periodicalService;
        this.topicService = topicService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int page = 1;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<Periodical> periodicals = periodicalService.getAllPeriodicalsByPrice(page);
        req.setAttribute("periodicals", periodicals);
        List<Topic> topics = topicService.getAllTopics();
        req.setAttribute("topics", topics);
        List<Integer> pages = Utility.countPages(periodicalService.getAllPeriodicals());
        req.setAttribute("pages", pages);
        return "/WEB-INF/view/periodicals_for_admin.jsp";
    }
}
