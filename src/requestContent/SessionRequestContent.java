package requestContent;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {

    private HttpServletRequest request;
    private Map<String, String> values = new HashMap<>();
    private Map<String, Object> reqAttributes = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();
    private HttpSession session;

    public SessionRequestContent(HttpServletRequest request) {
        this.request = request;
        session = request.getSession(true);
    }



    public void getSessionsAttributes() {
        Enumeration sessionList = session.getAttributeNames();
        while (sessionList.hasMoreElements()) {
            String sessionAttrName = (String) sessionList.nextElement();
            sessionAttributes.put(sessionAttrName, session.getAttribute(sessionAttrName));

        }
    }

    public void addAttrToSession(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    public void insertSessionAttributes() {
        for (Map.Entry<String, Object> mm : sessionAttributes.entrySet()) {
            session.setAttribute(mm.getKey(), mm.getValue());
        }
    }


    public void extractValues() {
        Enumeration parametersList = request.getParameterNames();
        while (parametersList.hasMoreElements()) {
            String paramName = (String) parametersList.nextElement();
            String[] paramValues = request.getParameterValues(paramName);

            for (String pv : paramValues) {
                values.put(paramName, pv);
            }
        }

    }


    public void insertAttributes() {
        for (Map.Entry<String, Object> mm : reqAttributes.entrySet()) {
            request.setAttribute(mm.getKey(), mm.getValue());
        }
    }

    public String getValueByName(String name) {

        return values.get(name);
    }

    public void addToAttributes(String key, String value) {
        reqAttributes.put(key, value);
    }

    public Object getSessionAttrByName(String name){
        return sessionAttributes.get(name);
    }


}
