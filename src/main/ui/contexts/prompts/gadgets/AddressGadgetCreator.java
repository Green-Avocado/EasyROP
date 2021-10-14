package ui.contexts.prompts.gadgets;

import model.gadgets.AddressGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

// Represents a UI context that creates a new AddressGadget
public class AddressGadgetCreator extends PromptContext {
    private String base;

    public AddressGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public AddressGadgetCreator(ConsoleContext parentContext, String base) {
        super(parentContext, "Offset", "0");

        this.base = base;
    }

    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new AddressGadgetCreator(getParentContext(), input);
        } else {
            return new GetIndex(getParentContext(), new AddressGadget(base, input));
        }
    }
}
