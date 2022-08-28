package app.web.controller.commands.container;

import app.dao.DbException;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class represents the command to send error.
 */
public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        req.setAttribute("wrong", "101");
        return "/WEB-INF/view/error_page.jsp";
    }
}
