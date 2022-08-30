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

/**
 * The class represents the command to go to edit form.
 */
public class EditPeriodicalFormCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(EditPeriodicalFormCommand.class);

    private final PeriodicalService periodicalService;

    /**
     * The constructor used to initialize services.
     *
     * @param periodicalService    {@see app.web.service.PeriodicalServiceImpl}
     */
    public EditPeriodicalFormCommand( PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        Periodical periodical = null;
        int id = 0;
        try {
            id = Integer.parseInt(Utility.validateData(req.getParameter("id")));
            periodical = periodicalService.getPeriodicalById(id);
        } catch (DbException | IOException e) {
            LOGGER.error("Failed to get periodical");
            throw new CommandException(e);
        }
        req.setAttribute("periodical", periodical);
        return "/WEB-INF/view/edit_periodical_form.jsp";
    }
}
