package server.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Samuel on 29-4-2015.
 */
public class MonteurAuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest)req).getSession().getAttribute("monteur") == null) {
            req.getRequestDispatcher("...").forward(req, resp); //TODO
        }
        else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
