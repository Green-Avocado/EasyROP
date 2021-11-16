package ui.cli.menus;

import ui.cli.ConsoleContext;

import java.util.List;

// Represents a UI context an option is selected from a given list of possible choices
public abstract class MenuContext extends ConsoleContext {

    // EFFECTS: Returns a MenuContext with the given parentContext.
    public MenuContext(ConsoleContext parentContext) {
        super(parentContext);
    }

    // EFFECTS: Returns a string of the menu options.
    @Override
    public String getContextString() {
        return String.join("  ", getMenu()) + "\n";
    }

    // EFFECTS: Returns the list of menu options.
    abstract List<String> getMenu();
}
