package tp2.tp2_akachar_el_barae_m2i.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;
        String requestURI = httpReq.getRequestURI();
        String path = requestURI.substring(httpReq.getContextPath().length());
        boolean isLoginPage = path.endsWith("login.html") || path.endsWith("LoginServlet");

        if (!isLoginPage) {
            HttpSession session = httpReq.getSession(false);

            if (session == null || session.getAttribute("utilisateur") == null) {
                httpRes.sendRedirect(httpReq.getContextPath() + "/login.html");
                return;
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}