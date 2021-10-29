package ui.contexts;

// Represents a UI context that displays the Python code for a GadgetCollection
public class TextViewer extends ConsoleContext {
    private final String string;

    // EFFECTS: Creates a new TextViewer context with a given parentContext and script.
    public TextViewer(ConsoleContext parentContext, String string) {
        super(parentContext);

        this.string = string + "\n";
    }

    // EFFECTS: Returns the script of this object.
    @Override
    public String getContextString() {
        return string;
    }

    // EFFECTS: Returns the parentContext.
    @Override
    public ConsoleContext handleInput(String input) {
        return getParentContext();
    }
}
