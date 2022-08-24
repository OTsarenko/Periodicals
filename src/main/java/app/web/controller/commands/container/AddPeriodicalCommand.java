package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Topic;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.TopicService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddPeriodicalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddPeriodicalCommand.class);

    private final PeriodicalService periodicalService;
    private final TopicService topicService;

    public AddPeriodicalCommand(PeriodicalService periodicalService, TopicService topicService) {
        this.periodicalService = periodicalService;
        this.topicService = topicService;
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        String engTitle = req.getParameter("engTitle");
        String ukrTitle = req.getParameter("ukrTitle");
        String engDescription = req.getParameter("engDescription");
        String ukrDescription = req.getParameter("ukrDescription");
        int issue = Integer.parseInt(req.getParameter("issue"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String[] topicList = req.getParameterValues("topic");
        List<Topic> topics = new ArrayList<>();
        for (String t: topicList){
            topics.add(topicService.getTopicById(Integer.parseInt(t)));
        }
        Periodical periodical = new Periodical();
        periodical.setEngTitle(engTitle);
        periodical.setUkrTitle(ukrTitle);
        periodical.setEngDescription(engDescription);
        periodical.setUkrDescription(ukrDescription);
        periodical.setIssue(issue);
        periodical.setPrice(price);
        try{
            periodicalService.insertPeriodical(periodical);
            for(Topic t: topics) {
                periodicalService.setTopicForPeriodical(t,periodical);
            }
        } catch (DbException e){
            LOGGER.info("Not enough data:"+engTitle+" "+ukrTitle+" "+engDescription+" "+ukrDescription+" "+issue+" "+price +e);
            return "app?command=periodicalsForAdmin&wrong=1";
        }
        return "app?command=periodicalsForAdmin";
    }
}
