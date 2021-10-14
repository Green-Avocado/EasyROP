package ui.contexts.prompts.gadgets;

import model.gadgets.Padding;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

// Represents a UI element that creates a new Padding object
public class PaddingCreator extends PromptContext {
    public PaddingCreator(ConsoleContext parentContext) {
        super(parentContext, "Padding length", "8");
    }

    public ConsoleContext handleInputInternal(String input) {
        int length = Integer.parseInt(input);

        if (length < 0) {
            return getParentContext();
        }

        return new GetIndex(getParentContext(), new Padding(length));
    }
}
