package com.tsystems.javaschool.servlets.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class AuthFilter implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (active){
            String email = (String) servletRequest.getAttribute("email");
            if (email == null) {
                request.setAttribute("error", "Please, write your e-mail");
                request.getRequestDispatcher("login.jsp").forward(request, servletResponse);
            }
            String password = (String) servletRequest.getAttribute("password");
            if (password == null) {
                request.setAttribute("error", "Please, write your password");
                request.getRequestDispatcher("login.jsp").forward(request, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        config = null;
    }
}
