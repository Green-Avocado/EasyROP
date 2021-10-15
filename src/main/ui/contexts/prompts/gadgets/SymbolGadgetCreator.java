package ui.contexts.prompts.gadgets;

import model.gadgets.SymbolGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.AddExploitObjectToIndex;

// Represents a UI context that creates a new SymbolGadget
public class SymbolGadgetCreator extends PromptContext {
    private String base;
    private String type;

    // EFFECTS: Creates a new SymbolGadgetCreator context with no base or type.
    public SymbolGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    // EFFECTS: Creates a new SymbolGadgetCreator context with a set base and no type
    public SymbolGadgetCreator(ConsoleContext parentContext, String base) {
        super(parentContext, "Type", "sym");

        this.base = base;
    }

    // EFFECTS: Creates a new SymbolGadgetCreator context with a set base and type
    public SymbolGadgetCreator(ConsoleContext parentContext, String base, String type) {
        super(parentContext, "Symbol", "system");

        this.base = base;
        this.type = type;
    }

    // EFFECTS: If the base is null, set the base as input. Otherwise, if the type is null, set the type as input.
    //          Otherwise, create a new SymbolGadget with the existing base and type, and with input as the symbol,
    //          passing this object to the new AddExploitObjectToIndex context.
    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new SymbolGadgetCreator(getParentContext(), input);
        } else if (type == null) {
            return new SymbolGadgetCreator(getParentContext(), base, input);
        } else {
            return new AddExploitObjectToIndex(getParentContext(), new SymbolGadget(base, type, input));
        }
    }
}
