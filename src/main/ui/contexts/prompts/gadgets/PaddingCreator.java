package ui.contexts.prompts.gadgets;

import model.gadgets.Padding;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.AddExploitObjectToIndex;

// Represents a UI element that creates a new Padding object
public class PaddingCreator extends PromptContext {
    public PaddingCreator(ConsoleContext parentContext) {
        super(parentContext, "Padding length", "8");
    }

    public ConsoleContext handleInputInternal(String input) {
        return new AddExploitObjectToIndex(getParentContext(), new Padding(input));
    }
}
