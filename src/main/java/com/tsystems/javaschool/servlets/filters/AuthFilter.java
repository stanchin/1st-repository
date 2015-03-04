package com.tsystems.javaschool.servlets.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class);
    private String[] pathToBeIgnored = new String[2];

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        pathToBeIgnored[0] = filterConfig.getInitParameter("pathToBeIgnored1");
        pathToBeIgnored[1] = filterConfig.getInitParameter("pathToBeIgnored2");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        if(session == null){
            for (String path : pathToBeIgnored) {
                if (!uri.contains(path)) {
                    LOGGER.debug("Unauthorized access request");
                    res.sendRedirect("login.jsp");
                }
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        this.pathToBeIgnored = null;
    }
}
