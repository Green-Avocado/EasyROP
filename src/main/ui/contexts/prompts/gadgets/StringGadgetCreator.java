package ui.contexts.prompts.gadgets;

import model.gadgets.StringGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.AddExploitObjectToIndex;

// Represents a UI context that creates a new StringGadget
public class StringGadgetCreator extends PromptContext {
    private String base;

    // EFFECTS: Creates a new StringGadgetCreator with no set base
    public StringGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    // EFFECTS: Creates a new StringGadgetCreator with the given base
    public StringGadgetCreator(ConsoleContext parentContext, String base) {
        super(parentContext, "String", "/bin/sh\\x00");

        this.base = base;
    }

    // EFFECTS: If base is null, set base as input.
    //          Otherwise, create a new StringGadget with the existing base and input as the string,
    //          passing this object to the new AddExploitObjectToIndex context.
    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new StringGadgetCreator(getParentContext(), input);
        } else {
            return new AddExploitObjectToIndex(getParentContext(), new StringGadget(base, input));
        }
    }
}
