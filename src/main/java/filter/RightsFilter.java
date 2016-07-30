package main.java.filter;


import main.java.dao.DAOAccount;
import main.java.dao.DAOFactory;
import main.java.dao.DAOUser;
import main.java.entities.Account;
import main.java.entities.User;
import main.java.managers.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The RightsFilter class defines if user is administrator ,
 * this filter turns him to the admin page, if not, user goes to users page.
 *
 * For administrator all information about locking bank accounts saves into session.
 *
 * It is implements Filter interface, and overrides three methods:
 * init(FilterConfig filterConfig),
 * doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
 * FilterChain filterChain) and destroy().
 *
 * @author Igor Gerasymenko
 */
public class RightsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * This method defines if user is administrator ,
     * this filter turns him to the admin page, if not, user goes to users page.
     *
     * For administrator all information about locking bank accounts saves into session.
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
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOUser daoUser = daoFactory.getDAOUser();

        String logIn = req.getParameter("email");
        String password = req.getParameter("password");

        boolean isExist = daoUser.isExist(logIn, password);


        if (isExist) {
            User user = daoUser.getUser(logIn, password);

            if (daoUser.isAdmin(user.getId())) {
                RequestDispatcher dispatcher = servletRequest.getServletContext()
                        .getRequestDispatcher(ConfigurationManager
                                .getProperty("path.page.admin"));
                DAOFactory factory = DAOFactory.getInstance();
                DAOAccount daoAccount = factory.getDAOAccount();
                List<Account> lockedList = daoAccount.getAllLockedAccounts();
                req.getSession().setAttribute("lockedList", lockedList);
                req.getSession().setAttribute("path",ConfigurationManager
                        .getProperty("path.page.admin"));


                dispatcher.forward(req, resp);
                return;
            } else {
                servletRequest.getRequestDispatcher(req.getServletPath())
                        .forward(req, servletResponse);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
