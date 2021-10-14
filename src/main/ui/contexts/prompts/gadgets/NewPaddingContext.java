package ui.contexts.prompts.gadgets;

import model.gadgets.Padding;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.NewIndexContext;

public class NewPaddingContext extends PromptContext {
    public NewPaddingContext(ConsoleContext parentContext) {
        super(parentContext, "Padding length", "8");
    }

    public ConsoleContext handleInputInternal(String input) {
        int length = Integer.parseInt(input);

        if (length < 0) {
            return getParentContext();
        }

        return new NewIndexContext(getParentContext(), new Padding(length));
    }
}
