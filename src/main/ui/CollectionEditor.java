package ui;

import model.GadgetCollection;

import java.util.Arrays;
import java.util.List;

public abstract class CollectionEditor extends MenuContext {
    String defaultName;

    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext) {
        super(collection, parentContext);
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

    abstract ConsoleContext defaultAction();

    abstract void add();

    abstract void open();

    abstract void print();

    abstract void reset();
}
