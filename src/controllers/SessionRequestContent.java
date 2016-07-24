package controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {

    private HttpServletRequest request;
    private Map<String, String> values = new HashMap<>();
    private Map<String, Object> reqAttributes = new HashMap<>();

    public SessionRequestContent(HttpServletRequest request) {
        this.request = request;

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

    public void insertAttributesToRequest() {
        for (Map.Entry<String, Object> mm : reqAttributes.entrySet()) {
            request.setAttribute(mm.getKey(), mm.getValue());
        }
    }



    public String getValueByName(String name) {
        return values.get(name);
    }

    public Map<String, Object> getReqAttributes() {
        return reqAttributes;
    }


    public HttpSession getSession() {
        return request.getSession(true);
    }
}
