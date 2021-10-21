package ui.contexts;

// Represents a UI context that displays the Python code for a GadgetCollection
public class ScriptViewer extends ConsoleContext {
    private final String script;

    // EFFECTS: Creates a new ScriptViewer context with a given parentContext and script.
    public ScriptViewer(ConsoleContext parentContext, String script) {
        super(parentContext);

        this.script = script + "\n";
    }

    // EFFECTS: Returns the script of this object.
    public String getContextString() {
        return script;
    }

    // EFFECTS: Returns the parentContext.
    public ConsoleContext handleInput(String input) {
        return getParentContext();
    }
}
