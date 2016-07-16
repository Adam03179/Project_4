package controllers;

import commands.Command;
import commands.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Handler extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Handler.class);

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        Command handler = CommandFactory.getInstance().getCommand(request);
        String path = null;
        try {
            path = handler.execute(request,response);
        } catch (SQLException e) {
           logger.error("process request error", e);
        }

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request,response);

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
