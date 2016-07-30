package main.java.filter;


import javax.servlet.*;
import java.io.IOException;


/**
 * The CharsetFilter class responds for encoding requests and responses to UTF-8 format.
 * It is implements Filter interface, and overrides three methods:
 * init(FilterConfig filterConfig),
 * doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
 * FilterChain filterChain) and destroy().
 *
 * @author Igor Gerasymenko
 */
public class CharsetFilter implements Filter {
    private String encoding;

    /**
     * This method initializes String encoding in "UTF-8"
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("requestEncoding");

        if (encoding == null) {
            encoding = "UTF-8";
        }

    }

    /**
     * This method encoding requests and responses to UTF-8 format.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws
            IOException, ServletException {

        if (servletRequest.getCharacterEncoding() == null) {
            servletRequest.setCharacterEncoding(encoding);
        }

        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
