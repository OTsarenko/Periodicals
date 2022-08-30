package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Topic;
import app.web.controller.commands.Command;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * The class represents the command to get first page.
 */
public class FirstPageCommand implements Command {

    private final PeriodicalService periodicalService;

    private final TopicService topicService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     * @param topicService         {@see app.web.service.TopicServiceImpl}
     */
    public FirstPageCommand(PeriodicalService periodicalService, TopicService topicService) {
        this.periodicalService = periodicalService;
        this.topicService = topicService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DbException {
       

        List<Topic> topicList = topicService.getAllTopics();
        Map<Topic, List<Periodical>> periodicals = periodicalService.getAllPeriodicalsByTopics(topicList);
        req.setAttribute("topics", topicList);
        req.setAttribute("periodicals", periodicals);


        return "/WEB-INF/view/first_page.jsp";
    }
}
