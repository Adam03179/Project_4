package controllers;

import commands.CommandFactory;
import commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Command handler = CommandFactory.getInstance().defineCommand(request);
        String path = null;

        SessionRequestContent requestContent = new SessionRequestContent(request);
        requestContent.extractValues();

            path = handler.execute(requestContent);

        requestContent.insertAttributesToRequest();
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
