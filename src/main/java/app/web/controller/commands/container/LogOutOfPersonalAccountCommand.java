package app.web.controller.commands.container;

import app.dao.DbException;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutOfPersonalAccountCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        HttpSession session = req.getSession();
        session.invalidate();
        return "app?command=";
    }
}
