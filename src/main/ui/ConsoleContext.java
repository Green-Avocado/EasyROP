package ui;

import model.GadgetCollection;

public abstract class ConsoleContext {
    private final ConsoleContext parentContext;

    private GadgetCollection collection;

    ConsoleContext(GadgetCollection collection, ConsoleContext parentContext) {
        this.collection = collection;
        this.parentContext = parentContext;
    }

    public ConsoleContext getParentContext() {
        return parentContext;
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
