package ui.contexts.prompts;

import model.gadgets.SymbolGadget;
import ui.contexts.ConsoleContext;

public class NewSymbolGadgetContext extends PromptContext {
    private String base;
    private String type;

    public NewSymbolGadgetContext(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public NewSymbolGadgetContext(ConsoleContext parentContext, String base) {
        super(parentContext, "Type", "sym");

        this.base = base;
    }

    public NewSymbolGadgetContext(ConsoleContext parentContext, String base, String type) {
        super(parentContext, "Symbol", "system");

        this.base = base;
        this.type = type;
    }

    ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new NewSymbolGadgetContext(getParentContext(), input);
        } else if (type == null) {
            return new NewSymbolGadgetContext(getParentContext(), base, input);
        } else {
            return new NewIndexContext(getParentContext(), new SymbolGadget(base, type, input));
        }
    }
}
