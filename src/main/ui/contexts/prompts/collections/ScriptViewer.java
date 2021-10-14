package ui.contexts.prompts.collections;

import ui.contexts.ConsoleContext;

public class ScriptViewer extends ConsoleContext {
    private final String script;

    public ScriptViewer(ConsoleContext parentContext, String script) {
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
