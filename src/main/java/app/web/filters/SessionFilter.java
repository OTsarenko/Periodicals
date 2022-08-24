package app.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The class contains a filter that restricts access for unregistered users.
 */
public class SessionFilter implements Filter {

    private ArrayList<String> unavailableCommandList;

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String requestCommand = req.getRequestURI()+"?"+ req.getQueryString();
        boolean shouldBeUnavailable = isUnavailableCommand(requestCommand);
        if(shouldBeUnavailable&&req.getSession().getAttribute("user")==null){
            res.sendRedirect(req.getContextPath()+"/app?command=logInForm");
        }else {
            chain.doFilter(servletRequest,servletResponse);
        }
    }

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
