package ui.cliContexts;

// Represents an arbitrary UI context with a given string, parent context, and input handler.
public abstract class ConsoleContext {
    private final ConsoleContext parentContext;

    // EFFECTS: Creates a new ConsoleContext with a given parentContext.
    public ConsoleContext(ConsoleContext parentContext) {
        this.parentContext = parentContext;
    }

    // EFFECTS: Returns the parentContext of this object.
    public ConsoleContext getParentContext() {
        return parentContext;
    }

    // EFFECTS: Accepts a user input string and returns the next ConsoleContext.
    public abstract ConsoleContext handleInput(String input);

    // EFFECTS: Returns the context string for this object.
    public abstract String getContextString();
}
