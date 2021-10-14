package ui;

public class PrintScriptContext extends ConsoleContext {
    private final String script;

    public PrintScriptContext(ConsoleContext parentContext, String script) {
        super(parentContext);

        this.script = script;
    }

    public String getContextString() {
        return script;
    }

    ConsoleContext handleInput(String input) {
        return getParentContext();
    }
}
