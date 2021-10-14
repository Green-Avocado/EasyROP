package ui;

import model.GadgetCollection;

public abstract class ConsoleContext {
    private GadgetCollection collection;

    ConsoleContext(GadgetCollection collection) {
        this.collection = collection;
    }

    public void setCollection(GadgetCollection collection) {
        this.collection = collection;
    }

    public GadgetCollection getCollection() {
        return collection;
    }

    abstract ConsoleContext handleInput(String input);

    abstract String getContextString();
}
