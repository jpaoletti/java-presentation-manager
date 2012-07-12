package jpaoletti.jpm;

import java.io.IOException;
import javax.servlet.*;

/**
 *
 * @author jpaoletti
 */
public class CharsetFilter implements Filter {

    public static final String CHARSET = "UTF-8";
    private String encoding;

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = CHARSET;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=" + CHARSET);
        response.setCharacterEncoding(CHARSET);
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
