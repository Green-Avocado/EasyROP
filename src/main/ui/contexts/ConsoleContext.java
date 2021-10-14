package ui.contexts;

// Represents an arbitrary UI context with a given string, parent context, and input handler
public abstract class ConsoleContext {
    private final ConsoleContext parentContext;

    public ConsoleContext(ConsoleContext parentContext) {
        this.parentContext = parentContext;
    }

    public ConsoleContext getParentContext() {
        return parentContext;
    }

    public abstract ConsoleContext handleInput(String input);

    public abstract String getContextString();
}
