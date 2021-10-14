package ui;

import model.GadgetCollection;

import java.util.Arrays;
import java.util.List;

public abstract class CollectionEditor extends MenuContext {
    private final String defaultName;

    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext, String defaultName) {
        super(collection, parentContext);
        this.defaultName = defaultName;
    }

    public String getContextString() {
        return getCollection().getName() + "\n" + super.getContextString();
    }

    public ConsoleContext handleInput(String input) {
        switch (input) {
            case "r":
                reset();
                return this;
            case "q":
                return getParentContext();
            default:
                return defaultAction();
        }
    }

    boolean delete(int index) {
        return getCollection().remove(index);
    }

    List<String> getMenu() {
        return Arrays.asList("[r]eset", "[q]uit");
    }

    void setName(String name) {
        getCollection().setName(name);
    }

    public String getDefaultName() {
        return defaultName;
    }

    abstract ConsoleContext defaultAction();

    abstract void add();

    abstract void open();

    abstract void print();

    abstract void reset();
}
