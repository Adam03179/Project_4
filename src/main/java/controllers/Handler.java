package main.java.controllers;

import main.java.commands.CommandFactory;
import main.java.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Handler class is HTTP servlet for this Project. It extends from HttpServlet class and,
 * overrides two his methods:
 * protected void doGet(HttpServletRequest req, HttpServletResponse resp),
 * protected void doPost(HttpServletRequest req, HttpServletResponse resp).
 * Also he have his own method, which works with users request and response.
 *
 * @author Igor Gerasymenko
 */
public class Handler extends HttpServlet {

    /**
     * This method send all requests to SessionRequestWrapper class for further processing.
     * It also have forward method, which pushes treated data to user.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionRequestWrapper requestContent = new SessionRequestWrapper(request);

        Command command = CommandFactory.getInstance().defineCommand(requestContent);
        String path = command.execute(requestContent);

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }
}
