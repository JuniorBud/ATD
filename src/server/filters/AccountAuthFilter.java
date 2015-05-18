package server.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccountAuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest)req).getSession().getAttribute("user") == null) {
            req.getRequestDispatcher("...").forward(req, resp);
        }
        else {
            chain.doFilter(req,resp);
        }

    }

    @Override
    public void destroy() {

    }
}
