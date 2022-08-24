package app.web.controller.commands;

import app.dao.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String execute (HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException;
}
