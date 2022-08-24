package app.web.listener;

import app.dao.interfaces.SubscribeDAO;
import app.dao.interfaces.TopicDAO;
import app.dao.interfaces.UserDAO;
import app.dao.mysql.SubscribeDAOImpl;
import app.dao.mysql.TopicDAOImpl;
import app.dao.mysql.UserDAOImpl;
import app.web.controller.commands.container.*;
import app.dao.interfaces.PeriodicalDAO;
import app.dao.mysql.PeriodicalDAOImpl;
import app.web.controller.commands.Command;
import app.web.controller.commands.CommandContainer;
import app.web.service.*;
import app.web.service.interfacas.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;

/**
 * The class for observations by context.
 */
@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("Start context initialization");
        ServletContext context = sce.getServletContext();
        initServices(context);
        LOGGER.debug("Services initialized");
    }

    private void initServices(ServletContext context) {
        // create Dao
        PeriodicalDAO periodicalDao = new PeriodicalDAOImpl();
        LOGGER.trace("created 'periodicalDao': "+ periodicalDao);

        UserDAO userDAO = new UserDAOImpl();
        LOGGER.trace("created 'userDao': "+ userDAO);

        SubscribeDAO subscribeDAO = new SubscribeDAOImpl();
        LOGGER.trace("created 'subscribeDao': "+ subscribeDAO);

        TopicDAO topicDAO = new TopicDAOImpl();
        LOGGER.trace("created 'topicDao': "+ topicDAO);

        // create services
        PeriodicalService periodicalService = new PeriodicalServiceImpl(periodicalDao);
        context.setAttribute("periodicalService", periodicalService);
        LOGGER.trace("context.setAttribute 'addPeriodicalService': "+periodicalService);

        SubscribeService subscribeService = new SubscribeServiceImpl(subscribeDAO, userDAO, periodicalDao);
        context.setAttribute("subscribeService", subscribeService);
        LOGGER.trace("context.setAttribute 'addSubscribeService': "+subscribeService);

        UserService userService = new UserServiceImpl(userDAO, subscribeDAO);
        context.setAttribute("userService", userService);
        LOGGER.trace("context.setAttribute 'addUserService': "+userService);

        TopicService topicService = new TopicServiceImpl(topicDAO);
        context.setAttribute("topicService", topicService);
        LOGGER.trace("context.setAttribute 'addTopicService': "+topicService);

        ReaderAlertService readerAlertService = new ReaderAlertServiceImpl(periodicalDao, userService);
        context.setAttribute("readerAlertService", readerAlertService);
        LOGGER.trace("context.setAttribute 'addService': "+readerAlertService);

        CommandContainer commands = new CommandContainer();
        Command command = new FirstPageCommand(periodicalService, topicService);
        commands.addCommand(null, command);
        commands.addCommand("", command);
        command = new AddPeriodicalCommand(periodicalService, topicService);
        commands.addCommand("addPeriodical", command);
        command = new AllPeriodicalsByPriceCommand(periodicalService);
        commands.addCommand("allPeriodicalsByPrice", command);
        command = new AllPeriodicalsByUkrTitleCommand(periodicalService);
        commands.addCommand("allPeriodicalsByUkrTitle", command);
        command = new AllPeriodicalsByEngTitleCommand(periodicalService);
        commands.addCommand("allPeriodicalsByEngTitle", command);
        command = new AllUsersCommand(userService);
        commands.addCommand("allUsers", command);
        command = new SetLocaleCommand(userService);
        commands.addCommand("setLocale", command);
        command = new BlockingUserCommand(userService);
        commands.addCommand("blockingUser", command);
        command = new DeletePeriodicalCommand(periodicalService);
        commands.addCommand("deletePeriodical", command);
        command = new EditPeriodicalFormCommand(periodicalService);
        commands.addCommand("editPeriodicalForm", command);
        command = new EditPeriodicalCommand(periodicalService, readerAlertService);
        commands.addCommand("editPeriodical", command);
        command = new EditUserCommand(userService);
        commands.addCommand("editUser", command);
        command = new PeriodicalsForAdminCommand(periodicalService, topicService);
        commands.addCommand("periodicalsForAdmin", command);
        command = new FindPeriodicalByTitleCommand(periodicalService);
        commands.addCommand("findPeriodical", command);
        command = new LogInCommand( userService);
        commands.addCommand("logIn", command);
        command = new LogInFormCommand();
        commands.addCommand("logInForm", command);
        command = new LogOutOfPersonalAccountCommand();
        commands.addCommand("logOut", command);
        command = new PeriodicalsByTopicCommand(topicService, periodicalService);
        commands.addCommand("periodicalsByTopic", command);
        command = new PersonalAccountCommand(subscribeService, userService, periodicalService);
        commands.addCommand("personalAccount", command);
        command = new RegistrationCommand(subscribeService,userService);
        commands.addCommand("registration", command);
        command = new RegistrationFormCommand();
        commands.addCommand("registrationForm", command);
        command = new ReplenishAccountCommand(userService);
        commands.addCommand("replenishAccount", command);
        command = new SubscribeCommand(subscribeService, userService, periodicalService);
        commands.addCommand("subscribe", command);
        command = new SubscribeFormCommand(periodicalService);
        commands.addCommand("subscribeForm", command);
        command = new UnblockingUserCommand(userService);
        commands.addCommand("unblockingUser", command);

        context.setAttribute("commandContainer", commands);
    }
}
