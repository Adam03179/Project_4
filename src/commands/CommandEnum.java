package commands;

public enum CommandEnum {

    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }};


    Command command;
    public Command getCurrentCommand() {
        return command;
    }

}
