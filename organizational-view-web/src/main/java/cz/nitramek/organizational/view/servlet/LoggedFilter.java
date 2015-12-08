package cz.nitramek.organizational.view.servlet;

import cz.nitramek.organizational.view.beans.NavigationRules;
import cz.nitramek.organizational.view.beans.SessionBackingBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoggedFilter", urlPatterns = {"/", "*"})
public class LoggedFilter implements Filter {

    @Inject
    private SessionBackingBean ssb;

    public void destroy() {
    }

    public void doFilter(
            ServletRequest req, ServletResponse resp,
            FilterChain chain) throws ServletException, IOException {


        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httResp = (HttpServletResponse) resp;
        ;
        if (this.ssb.isLogged() || httpReq.getServletPath().equals(NavigationRules.HOME)) {
            chain.doFilter(req, resp);
        } else {

            httResp.sendRedirect(httpReq.getContextPath() + NavigationRules.HOME);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
