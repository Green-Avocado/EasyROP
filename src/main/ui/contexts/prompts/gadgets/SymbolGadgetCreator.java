package ui.contexts.prompts.gadgets;

import model.gadgets.SymbolGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

// Represents a UI context that creates a new SymbolGadget
public class SymbolGadgetCreator extends PromptContext {
    private String base;
    private String type;

    public SymbolGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public SymbolGadgetCreator(ConsoleContext parentContext, String base) {
        super(parentContext, "Type", "sym");

        this.base = base;
    }

    public SymbolGadgetCreator(ConsoleContext parentContext, String base, String type) {
        super(parentContext, "Symbol", "system");

        this.base = base;
        this.type = type;
    }

    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new SymbolGadgetCreator(getParentContext(), input);
        } else if (type == null) {
            return new SymbolGadgetCreator(getParentContext(), base, input);
        } else {
            return new GetIndex(getParentContext(), new SymbolGadget(base, type, input));
        }
    }
}
