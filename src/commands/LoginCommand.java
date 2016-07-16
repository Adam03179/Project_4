package commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws SQLException {

        //lkasjdlaksjdlaksjdlaksjdalkdalkkdalkkjdlakksdlkasdlklkdlk

        return "/jsp/user/registration.jsp";
    }


}
