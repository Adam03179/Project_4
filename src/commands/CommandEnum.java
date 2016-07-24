package commands;

public enum CommandEnum {

    CHANGE_LANGUAGE {{
        this.command = new ChangeLanguageCommand();
    }},
    LOGIN {{
        this.command = new LoginCommand();
    }},
    REGISTRATION {{
        this.command = new RegistrationCommand();

    }},
    CANCEL {{
        this.command = new EmptyCommand();
    }},
    OPEN_ACCOUNT {{
        this.command = new OpenAccountCommand();
    }},
    SHOW_ACCOUNTS {{

        this.command = new ShowAccountsCommand();
    }},
    OPEN_CARD {{

        this.command = new OpenCardCommand();
    }},
    ATTACH_CARD {{
        this.command = new AttachCardCommand();
    }};


    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
