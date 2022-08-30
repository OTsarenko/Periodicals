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
 * The class represents the command to find periodicals by topic.
 */
public class PeriodicalsByTopicCommand implements Command {

    private final TopicService topicService;

    private final PeriodicalService periodicalService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     * @param topicService         {@see app.web.service.TopicServiceImpl}
     */
    public PeriodicalsByTopicCommand(TopicService topicService, PeriodicalService periodicalService) {
        this.topicService = topicService;
        this.periodicalService = periodicalService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {

        Topic topic = topicService.getTopicById(Integer.parseInt(req.getParameter("id")));
        List <Periodical> periodicals = periodicalService.getPeriodicalsByTopic(topic);
        req.setAttribute("periodicals", periodicals);
        List<Integer> pages = Utility.countPages(periodicals);
        req.setAttribute("pages", pages);

        return "/WEB-INF/view/all_periodicals.jsp";
    }
}
