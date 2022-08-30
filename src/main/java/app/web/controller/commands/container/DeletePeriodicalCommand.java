package app.web.controller.commands.container;

import app.dao.DbException;
import app.util.Utility;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import app.web.service.interfacas.PeriodicalService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class represents the command to delete periodical.
 */
public class DeletePeriodicalCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeletePeriodicalCommand.class);

    private final PeriodicalService periodicalService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     */
    public DeletePeriodicalCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {

        try {
            int id = Integer.parseInt(Utility.validateData(req.getParameter("id")));
            periodicalService.deletePeriodical(id);
        } catch (DbException | IOException e) {
            LOGGER.error("Cant delete periodicals"+e);
            throw new CommandException(e);
        }

        return "app?command=periodicalsForAdmin";
    }
}
