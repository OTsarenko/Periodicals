package app.web;

import app.dao.DbException;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandContainer;
import app.web.controller.commands.CommandException;
import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    CommandContainer commandContainer = new CommandContainer();
    Command mockCommand = new Command() {
        @Override
        public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException, DbException {
            return null;
        }
    };

    @Test
    void getAddress(){
        String commandName = "commandTest";
        assertEquals(null, commandContainer.getCommand(commandName));
    }
}
