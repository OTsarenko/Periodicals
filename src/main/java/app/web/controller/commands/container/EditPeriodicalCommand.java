package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.entity.Topic;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.ReaderAlertService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class EditPeriodicalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddPeriodicalCommand.class);

    private final PeriodicalService periodicalService;
    private final ReaderAlertService readerAlertService;

    public EditPeriodicalCommand(PeriodicalService periodicalService, ReaderAlertService readerAlertService) {
        this.periodicalService = periodicalService;
        this.readerAlertService = readerAlertService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        int id = Integer.parseInt(req.getParameter("id"));
        Periodical periodical = periodicalService.getPeriodicalById(id);
        if (req.getParameter("issue") != null) {
            int issue = Integer.parseInt(req.getParameter("issue"));
            periodical.setIssue(issue);
            readerAlertService.update(periodical);
        }

        if (req.getParameter("engTitle") != null) {
            String engTitle = req.getParameter("engTitle");
            String ukrTitle = req.getParameter("ukrTitle");
            String engDescription = req.getParameter("engDescription");
            String ukrDescription = req.getParameter("ukrDescription");
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            periodical.setEngTitle(engTitle);
            periodical.setUkrTitle(ukrTitle);
            periodical.setEngDescription(engDescription);
            periodical.setUkrDescription(ukrDescription);
            periodical.setPrice(price);
            try {
                periodicalService.updatePeriodical(periodical);
            } catch (DbException e) {
                LOGGER.info("Not enough data:" + engTitle + " " + ukrTitle + " " + engDescription + " " + ukrDescription + " "  + price + e);
                return "app?command=periodicalsForAdmin&wrong=1";
            }
        }

        return "app?command=periodicalsForAdmin&page=1";
    }
}
