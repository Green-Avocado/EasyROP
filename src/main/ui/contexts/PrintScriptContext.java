package ui.contexts;

public class PrintScriptContext extends ConsoleContext {
    private final String script;

    public PrintScriptContext(ConsoleContext parentContext, String script) {
        super(parentContext);

        this.script = script;
    }

    public String getContextString() {
        return script;
    }

    public ConsoleContext handleInput(String input) {
        return getParentContext();
    }
}
