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

import static app.util.Utility.validateData;


/**
 * The class represents the command to edit issue.
 */
public class EditIssueCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(EditIssueCommand.class);

    private final PeriodicalService periodicalService;

    private final ReaderAlertService readerAlertService;

    public EditIssueCommand(PeriodicalService periodicalService, ReaderAlertService readerAlertService) {
        this.periodicalService = periodicalService;
        this.readerAlertService = readerAlertService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        Periodical periodical = null;
        int id = 0;

        try {
            id = Integer.parseInt(validateData(req.getParameter("id")));
            periodical = periodicalService.getPeriodicalById(id);
        } catch (DbException | IOException e) {
            LOGGER.info("Cant get periodical"+ e);
            throw new CommandException(e);
        }
        if (req.getParameter("issue") != null) {
            int issue = Integer.parseInt(req.getParameter("issue"));
            periodical.setIssue(issue);
            try {
                readerAlertService.update(periodical);
            } catch (DbException e) {
                LOGGER.info("Cant update issue"+ e);
                throw new CommandException(e);
            }
        }
        return "app?command=periodicalsForAdmin&page=1";
    }
}
