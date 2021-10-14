package ui;

import model.GadgetCollection;

import java.util.Arrays;
import java.util.List;

public abstract class CollectionEditor extends MenuContext {
    private GadgetCollection collection;

    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext) {
        super(parentContext);

        this.collection = collection;
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "n":
                return add();
            case "o":
                return open();
            case "d":
                return delete();
            case "p":
                return print();
            case "r":
                reset();
                return this;
            case "q":
                return getParentContext();
            default:
                return defaultAction();
        }
    }

    public void setCollection(GadgetCollection collection) {
        this.collection = collection;
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

    abstract ConsoleContext defaultAction();

    abstract ConsoleContext add();

    abstract ConsoleContext open();

    abstract ConsoleContext delete();

    abstract ConsoleContext print();

    abstract ConsoleContext reset();
}
