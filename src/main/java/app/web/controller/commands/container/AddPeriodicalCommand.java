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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static app.util.Utility.validateData;

/**
 * The class represents the command to add periodical.
 */
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
        String engTitle = null;
        String ukrTitle = null;
        String engDescription = null;
        String ukrDescription = null;
        int issue = 0;
        BigDecimal price = null;
        try {
            engTitle = validateData( req.getParameter("engTitle"));
            ukrTitle = validateData(req.getParameter("ukrTitle"));
            engDescription = validateData(req.getParameter("engDescription"));
            ukrDescription = validateData(req.getParameter("ukrDescription"));
            issue = Integer.parseInt(validateData(req.getParameter("issue")));
            price = new BigDecimal(validateData(req.getParameter("price")));
        } catch (IOException e) {
            LOGGER.error("Not enough data:"+engTitle+" "+ukrTitle+" "+engDescription+" "+ukrDescription+" "+issue+" "+price +e);
            throw new CommandException(e);
        }

        String[] topicList = req.getParameterValues("topic");
        if (topicList == null || topicList.length == 0) { throw new CommandException("You didn't select topics");}
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
            periodicalService.setTopicsForNewPeriodicalTransaction(periodical, topics.toArray(new Topic[0]));
        } catch (DbException e){
            LOGGER.error("Failed to add periodical");
            throw new CommandException(e);
        }
        return "app?command=periodicalsForAdmin";
    }
}
