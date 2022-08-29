package app.web.controller.commands.container;

import app.dao.DbException;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class represents the command to go to registration form.
 */
public class RegistrationFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        return "/WEB-INF/view/user_registration_form.jsp";
    }
}
