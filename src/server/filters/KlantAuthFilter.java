package server.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Samuel on 1-5-2015.
 */
public class KlantAuthFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        if (((HttpServletRequest)req).getSession().getAttribute("klant") == null) {
            r.getRequestDispatcher("...").forward(req, resp);
        }
        else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
