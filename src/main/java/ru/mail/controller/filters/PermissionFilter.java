package ru.mail.controller.filters;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Denis Monich
 * this filter is for protection of admin information
 */
public class PermissionFilter implements Filter {

    private static final String LOGIN_URL = "/login";
    private static final Logger logger = Logger.getLogger(PermissionFilter.class);


    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * main method of the filter is doing logic work
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Permission filter is working");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //try to get user from session
        String user = (String) req.getSession().getAttribute("user");
        if (user == null) {
            //if user is not autheficated - redirect to login page
            resp.sendRedirect(req.getContextPath() + LOGIN_URL);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}