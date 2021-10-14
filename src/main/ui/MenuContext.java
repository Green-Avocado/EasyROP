package ui;

import model.GadgetCollection;

import java.util.List;

public abstract class MenuContext extends ConsoleContext {

    public MenuContext(ConsoleContext parentContext) {
        super(parentContext);
    }

    public String getContextString() {
        return String.join("  ", getMenu()) + "\n";
    }

    abstract List<String> getMenu();
}
