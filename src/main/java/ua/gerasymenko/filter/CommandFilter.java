package ua.gerasymenko.filter;

import ua.gerasymenko.managers.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * The CommandFilter class redirects the user to the appropriate page,
 * according to his request.
 * It is implements Filter interface, and overrides three methods:
 * init(FilterConfig filterConfig),
 * doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
 * FilterChain filterChain) and destroy().
 *
 * @author Igor Gerasymenko
 */
public class CommandFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * This method redirects the user to the appropriate page,
     * according to his request with parameter 'Confirm'.
     * It serves to unloading Commands classes.
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

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        String request = req.getParameter("Confirm");

        switch (request.toUpperCase()) {
            case "ACCOUNT_LOCK_MENU": {
                String path = ConfigurationManager.getProperty("path.page.lockAccount");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "DEPOSIT": {
                String path = ConfigurationManager.getProperty("path.page.deposit");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "LOG_OUT": {
                String path = ConfigurationManager.getProperty("path.page.index");
                pathSelect(path, servletRequest, servletResponse);
                session.invalidate();
                return;
            }
            case "OPEN_CARD": {
                String path = ConfigurationManager.getProperty("path.page.openCard");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "OPERATIONS": {
                String path = ConfigurationManager.getProperty("path.page.operations");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "SHOW_ACCOUNTS": {
                String path = ConfigurationManager.getProperty("path.page.showAccounts");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "SHOW_CARDS": {
                String path = ConfigurationManager.getProperty("path.page.showCards");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "SHOW_HISTORY": {
                String path = ConfigurationManager.getProperty("path.page.history");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "TRANSFER": {
                String path = ConfigurationManager.getProperty("path.page.transfer");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
            case "WITHDRAW_FUNDS": {
                String path = ConfigurationManager.getProperty("path.page.withdraw");
                pathSelect(path, servletRequest, servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {

    }

    /**
     * This method is sets required path to the session.
     *
     * @param str
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    private void pathSelect(String str, ServletRequest servletRequest,
                            ServletResponse servletResponse)
            throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        session.setAttribute("path", str);
        RequestDispatcher dispatcher = servletRequest.getServletContext()
                .getRequestDispatcher(str);

        dispatcher.forward(req, servletResponse);

    }
}
