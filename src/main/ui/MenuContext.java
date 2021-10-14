package ui;

import model.GadgetCollection;

import java.util.List;

public abstract class MenuContext extends ConsoleContext {
    private final ConsoleContext parentContext;

    public MenuContext(GadgetCollection collection, ConsoleContext parentContext) {
        super(collection);

        this.parentContext = parentContext;
    }

    public String getContextString() {
        return String.join("  ", getMenu()) + "\n";
    }

    public ConsoleContext getParentContext() {
        return parentContext;
    }

    abstract List<String> getMenu();
}
