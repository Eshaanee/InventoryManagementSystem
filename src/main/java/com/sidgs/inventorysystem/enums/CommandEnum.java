package com.sidgs.inventorysystem.enums;

public enum CommandEnum {

    CREATE(1, "create"),
    DELETE(2, "delete"),
    UPDATE_BUY(3, "updateBuy"),
    UPDATE_SELL(4, "updateSell"),
    REPORT(5, "report"),
    UPDATE_SELL_PRICE(6, "updateSellPrice"),
    EXIT(7, "exit");

    private final int id;
    private final String commandName;

    CommandEnum(int id, String commandName) {
        this.id = id;
        this.commandName = commandName;
    }

    public static CommandEnum getEnum(String value) {
        for (CommandEnum command : values())
            if (command.getCommandName().equalsIgnoreCase(value)) return command;
        return null;
    }

    public String getCommandName() {
        return commandName;
    }
}
