package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession(true);
        String language = request.getParameter("language");
        session.setAttribute("setLocale", language);
        String path = (String) session.getAttribute("path");
        if (path == null) {
            return "/jsp/index.jsp";
        } else {
            return (String) session.getAttribute("path");
        }
    }
}
