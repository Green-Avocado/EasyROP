package ui.contexts.prompts;

import model.gadgets.InstructionsGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.util.AddExploitObjectToIndex;

import java.util.ArrayList;

// Represents a UI context that creates a new InstructionsGadget
public class InstructionsGadgetCreator extends PromptContext {
    private String base;
    private ArrayList<String> list;

    public InstructionsGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public InstructionsGadgetCreator(ConsoleContext parentContext, String base, ArrayList<String> list) {
        super(parentContext, "Instruction/quit", "quit");

        this.base = base;
        this.list = list;
    }

    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new InstructionsGadgetCreator(getParentContext(), input, new ArrayList<>());
        } else if (!input.equalsIgnoreCase("quit")) {
            list.add(input.toLowerCase());
            return new InstructionsGadgetCreator(getParentContext(), input, list);
        } else if (list.isEmpty()) {
            return getParentContext();
        } else {
            return new AddExploitObjectToIndex(getParentContext(), new InstructionsGadget(base, list));
        }
    }
}
