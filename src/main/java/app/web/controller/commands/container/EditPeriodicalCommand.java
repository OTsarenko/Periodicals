package app.web.controller.commands.container;

import app.dao.DbException;
import app.entity.Periodical;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import app.web.service.interfacas.ReaderAlertService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import static app.util.Utility.validateData;

/**
 * The class represents the command to edit periodical.
 */
public class EditPeriodicalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(EditPeriodicalCommand.class);

    private final PeriodicalService periodicalService;

    private final ReaderAlertService readerAlertService;

    public EditPeriodicalCommand(PeriodicalService periodicalService, ReaderAlertService readerAlertService) {
        this.periodicalService = periodicalService;
        this.readerAlertService = readerAlertService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {

        Periodical periodical = null;
        String engTitle = null;
        String ukrTitle = null;
        String engDescription = null;
        String ukrDescription = null;
        BigDecimal price = null;
        int id = 0;

        try {
            id = Integer.parseInt(validateData(req.getParameter("id")));
            periodical = periodicalService.getPeriodicalById(id);
        } catch (DbException | IOException e) {
            LOGGER.info("Cant get periodical"+ e);
            throw new CommandException(e);
        }

        try {
            engTitle = validateData( req.getParameter("engTitle"));
            ukrTitle = validateData(req.getParameter("ukrTitle"));
            engDescription = validateData(req.getParameter("engDescription"));
            ukrDescription = validateData(req.getParameter("ukrDescription"));
            price = new BigDecimal(validateData(req.getParameter("price")));
        } catch (IOException e) {
            LOGGER.error("Not enough data:"+engTitle+" "+ukrTitle+" "+engDescription+" "+ukrDescription+" "+ price +e);
            throw new CommandException(e);
        }
        periodical.setEngTitle(engTitle);
        periodical.setUkrTitle(ukrTitle);
        periodical.setEngDescription(engDescription);
        periodical.setUkrDescription(ukrDescription);
        periodical.setPrice(price);
        try {
            periodicalService.updatePeriodical(periodical);
        } catch (DbException e) {
            LOGGER.info("Cant edit"+ e);
            throw new CommandException(e);
        }

        if (req.getParameter("issue") != null) {
            int issue = Integer.parseInt(req.getParameter("issue"));
            periodical.setIssue(issue);
            readerAlertService.update(periodical);
        }
        return "app?command=periodicalsForAdmin&page=1";
    }
}
