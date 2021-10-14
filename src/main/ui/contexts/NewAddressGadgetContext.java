package ui.contexts;

import model.gadgets.AddressGadget;

public class NewAddressGadgetContext extends PromptContext {
    private String base;

    public NewAddressGadgetContext(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public NewAddressGadgetContext(ConsoleContext parentContext, String base) {
        super(parentContext, "Offset", "0");

        this.base = base;
    }

    ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new NewAddressGadgetContext(getParentContext(), input);
        } else {
            return new NewIndexContext(getParentContext(), new AddressGadget(base, input));
        }
    }
}
