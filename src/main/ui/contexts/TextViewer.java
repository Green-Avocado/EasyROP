package ui.contexts;

// Represents a UI context that displays a given message to the user.
public class TextViewer extends ConsoleContext {
    private final String string;

    // EFFECTS: Creates a new TextViewer context with a given parentContext and text.
    public TextViewer(ConsoleContext parentContext, String string) {
        super(parentContext);

        this.string = string + "\n";
    }

    // EFFECTS: Returns the text of this object.
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
