package ua.gerasymenko.commands;

import org.junit.Test;

import org.mockito.Mockito;
import ua.gerasymenko.controllers.SessionRequestWrapper;
import ua.gerasymenko.managers.ConfigurationManager;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

public class ChangeLanguageCommandTest {

    private SessionRequestWrapper requestWrapper;
    private HttpSession session;
    private ChangeLanguageCommand command;

    @Test
    public void testExecute(){

        command = Mockito.mock(ChangeLanguageCommand.class);
        session = Mockito.mock(HttpSession.class);
        requestWrapper = Mockito.mock(SessionRequestWrapper.class);
        Mockito.when(requestWrapper.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("path")).thenReturn("test");
        Mockito.when(command.execute(requestWrapper)).thenCallRealMethod();

        assertEquals(command.execute(requestWrapper), "test");


    }
}