package app.web.filters;


import app.entity.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static app.enums.UserRole.ADMINISTRATOR;

/**
 * The class contains a filter that restricts access for users not having the role of admin.
 */
public class AdminAccessFilter  implements Filter {

    private ArrayList<String> unavailableCommandList;
    private User user;

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        unavailableCommandList = new ArrayList<>();
        String commands = filterConfig.getInitParameter("unavailable-command");
        StringTokenizer token = new StringTokenizer(commands, ",");
        while (token.hasMoreTokens()){
            unavailableCommandList.add(token.nextToken());
        }
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String requestCommand = req.getRequestURI()+"?"+ req.getQueryString();
        boolean shouldBeUnavailable = isUnavailableCommand(requestCommand);
        if (req.getSession().getAttribute("user") != null) {
            user = (User) req.getSession().getAttribute("user");
        }
        if(shouldBeUnavailable && (user.getUserRole() != ADMINISTRATOR)){
            res.sendRedirect(req.getContextPath()+"/app?command=error");
        }else {
            chain.doFilter(servletRequest,servletResponse);
        }
    }

    /**
     * Check the parameter command belongs to the list of unavailable commands
     * @param command command for checks
     * @return true - if command unavailable/ false - if available
     */
    private boolean isUnavailableCommand(String command){
        for (String unavailableCommand: getUnavailableCommandList()){
            if(command.startsWith(unavailableCommand)){
                return true;
            }
        }
        return false;
    }

    public List<String> getUnavailableCommandList(){
        return unavailableCommandList;
    }
}
