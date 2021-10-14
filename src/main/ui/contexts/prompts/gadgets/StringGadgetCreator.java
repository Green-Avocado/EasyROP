package ui.contexts.prompts.gadgets;

import model.gadgets.StringGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

// Represents a UI context that creates a new StringGadget
public class StringGadgetCreator extends PromptContext {
    private String base;

    public StringGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public StringGadgetCreator(ConsoleContext parentContext, String base) {
        super(parentContext, "String", "/bin/sh\\x00");

        this.base = base;
    }

    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new StringGadgetCreator(getParentContext(), input);
        } else {
            return new GetIndex(getParentContext(), new StringGadget(base, input));
        }
    }
}
