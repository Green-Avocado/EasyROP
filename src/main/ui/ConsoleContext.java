package ui;

public abstract class ConsoleContext {
    private final ConsoleContext parentContext;

    ConsoleContext(ConsoleContext parentContext) {
        this.parentContext = parentContext;
    }

    public ConsoleContext getParentContext() {
        return parentContext;
    }

    abstract ConsoleContext handleInput(String input);

    abstract String getContextString();
}
