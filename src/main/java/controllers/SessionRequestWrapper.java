package main.java.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The SessionRequestWrapper class responds for isolation of almost all methods of
 * HttpServletRequest class, and it leaves only two methods getParameter() and
 * getSession().
 *
 * @author Igor Gerasymenko
 */
public class SessionRequestWrapper {

    private HttpServletRequest request;


    public SessionRequestWrapper(HttpServletRequest request) {
        this.request = request;

    }

    /**
     * This method wrap of HttpServletRequest class method - getParameter().
     *
     * @param name
     * @return parameter by the name
     */
    public String getValueByName(String name) {
        return request.getParameter(name);
    }

    /**
     * This method wrap of HttpServletRequest class method - getSession().
     *
     * @return session with "true" parameter
     */
    public HttpSession getSession() {
        return request.getSession(true);
    }
}
