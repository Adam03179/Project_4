package ua.gerasymenko.filter;

import ua.gerasymenko.managers.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The SecurityFilter class doesn't allow users to go on the bank pages by  url.
 *
 * It is implements Filter interface, and overrides three methods:
 * init(FilterConfig filterConfig),
 * doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
 * FilterChain filterChain) and destroy().
 *
 * @author Igor Gerasymenko
 */
public class SecurityFilter implements Filter {

    //all  url of the bank, except the registration page and 'index' page.
    private final List<String> allUrls = new ArrayList<>();

    {
        allUrls.add("/jsp/header.jsp");
        allUrls.add("/jsp/admin/admin.jsp");
        allUrls.add("/jsp/user/accountLock.jsp");
        allUrls.add("/jsp/user/deposit.jsp");
        allUrls.add("/jsp/user/history.jsp");
        allUrls.add("/jsp/user/mainPage.jsp");
        allUrls.add("/jsp/user/openCard.jsp");
        allUrls.add("/jsp/user/operations.jsp");
        allUrls.add("/jsp/user/operationSuccess.jsp");
        allUrls.add("/jsp/user/showAccounts.jsp");
        allUrls.add("/jsp/user/showCards.jsp");
        allUrls.add("/jsp/user/success.jsp");
        allUrls.add("/jsp/user/transferFunds.jsp");
        allUrls.add("/jsp/user/withdraw.jsp");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * This method doesn't allow users to go on the bank pages by url.
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse
            servletResponse, FilterChain filterChain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (allUrls.contains(req.getServletPath())) {
            req.getRequestDispatcher(ConfigurationManager.getProperty
                    ("path.page.index")).forward(req, servletResponse);
        }

        req.getRequestDispatcher(req.getServletPath()).forward(req,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
