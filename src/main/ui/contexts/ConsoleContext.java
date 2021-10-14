package ui.contexts;

public abstract class ConsoleContext {
    private final ConsoleContext parentContext;

    ConsoleContext(ConsoleContext parentContext) {
        this.parentContext = parentContext;
    }

    public ConsoleContext getParentContext() {
        return parentContext;
    }

    public abstract ConsoleContext handleInput(String input);

    public abstract String getContextString();
}
