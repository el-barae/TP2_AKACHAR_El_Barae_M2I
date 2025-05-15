package tp2.tp2_akachar_el_barae_m2i.filters;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*", filterName = "CharsetEncodingFilter")
public class CharsetEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
        res.setCharacterEncoding("UTF-8");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
