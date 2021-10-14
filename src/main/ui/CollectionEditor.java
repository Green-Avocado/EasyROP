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

    public void setCollection(GadgetCollection collection) {
        this.collection = collection;
    }

    public GadgetCollection getCollection() {
        return collection;
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

    abstract void reset();
}
