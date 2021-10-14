package ui.contexts.menus;

import ui.contexts.ConsoleContext;

import java.util.List;

// Represents a UI context an option is selected from a given list of possible choices
public abstract class MenuContext extends ConsoleContext {

    public MenuContext(ConsoleContext parentContext) {
        super(parentContext);
    }

    public String getContextString() {
        return String.join("  ", getMenu()) + "\n";
    }

    abstract List<String> getMenu();
}
