package ui;

import model.ExploitObject;
import model.GadgetCollection;

import java.util.Arrays;
import java.util.List;

public abstract class CollectionEditor extends MenuContext {
    private GadgetCollection collection;

    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext) {
        super(parentContext);

        this.collection = collection;
    }

    public String getContextString() {
        StringBuilder contextString = new StringBuilder();

        contextString.append(getCollection().getName());
        contextString.append(":\n");

        for (ExploitObject exploitObject : getCollection().getList()) {
            contextString.append("  ");
            contextString.append(exploitObject.getName());
            contextString.append("\n");
        }

        contextString.append(super.getContextString());

        return contextString.toString();
    }


    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            default:
            case "n":
                return add();
            case "o":
                return open();
            case "m":
                return move();
            case "d":
                return delete();
            case "p":
                return print();
            case "r":
                return reset();
            case "q":
                return getParentContext();
        }
    }

    public GadgetCollection getCollection() {
        return collection;
    }

    List<String> getMenu() {
        return Arrays.asList("[r]eset", "[q]uit");
    }

    void setName(String name) {
        getCollection().setName(name);
    }

    abstract ConsoleContext add();

    abstract ConsoleContext open();

    abstract ConsoleContext move();

    abstract ConsoleContext delete();

    abstract ConsoleContext print();

    abstract ConsoleContext reset();
}
