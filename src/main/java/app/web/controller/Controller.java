package app.web.controller;

import app.dao.DbException;
import app.web.controller.commands.CommandContainer;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@WebServlet(name = "controller", value = "/app")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private CommandContainer commands;

    @Override
    public void init(ServletConfig config) throws ServletException {
        commands = (CommandContainer) config.getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String address = null;
        try {
            address = getAddress(req,resp);
        } catch (CommandException | DbException e) {
            LOGGER.debug("Error: {}", e);
            resp.sendError(500, "Can`t process the command");
        }
        req.getRequestDispatcher(address).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String address = null;
        try {
            address = getAddress(req, resp);
        } catch (CommandException | DbException e) {
            LOGGER.debug("Error: {}", e);
            resp.sendError(500, "Can`t process the command");
        }
        resp.sendRedirect(address);
    }

    public String getAddress(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
        String commandName = req.getParameter("command");
        Command command = commands.getCommand(commandName);
        return command.execute(req,resp);
    }
}