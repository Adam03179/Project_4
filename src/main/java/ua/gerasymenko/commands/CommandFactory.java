package ua.gerasymenko.commands;

import ua.gerasymenko.controllers.SessionRequestWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommandFactory class is Factory from Factory pattern.
 * This class is Singleton.
 * It includes only one method, defineCommand() which receives on request
 * the necessary command from commandMap and returns it.
 *
 * @author Igor Gerasymenko
 */
public class CommandFactory {
    private Map<String, Command> commandMap = new HashMap<>();

    private static CommandFactory instance = null;

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {

        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    {
        commandMap.put("change_language", ChangeLanguageCommand.getInstance());
        commandMap.put("Change_pass", ChangePasswordCommand.getInstance());
        commandMap.put("login", LoginCommand.getInstance());
        commandMap.put("registration", RegistrationCommand.getInstance());
        commandMap.put("cancel", EmptyCommand.getInstance());
        commandMap.put("open_account", OpenAccountCommand.getInstance());
        commandMap.put("Attach_Card", AttachCardCommand.getInstance());
        commandMap.put("Account_lock", AccountLockCommand.getInstance());
        commandMap.put("deposit_operation", DepositOperationCommand.getInstance());
        commandMap.put("withdraw_operation", WithdrawOperationCommand.getInstance());
        commandMap.put("transfer_operation", TransferOperationCommand.getInstance());
        commandMap.put("refresh", RefreshCommand.getInstance());
        commandMap.put("Account_unlock", AccountUnlockCommand.getInstance());

    }

    /**
     * This method extracts value with name "Confirm" from request and gets
     * needed command from commandMap and returns it.
     * If value is null, than method returns EmptyCommand.
     *
     * @param request
     * @return Command
     */
    public Command defineCommand(SessionRequestWrapper request) {

        Command current = EmptyCommand.getInstance();
        String action = request.getValueByName("Confirm");

        if (action == null || action.isEmpty()) {
            return current;
        }

        return commandMap.get(action);

    }

}
