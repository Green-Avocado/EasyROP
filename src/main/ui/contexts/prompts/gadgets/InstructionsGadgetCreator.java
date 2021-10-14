package ui.contexts.prompts.gadgets;

import model.gadgets.InstructionsGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

import java.util.ArrayList;

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
            return new GetIndex(getParentContext(), new InstructionsGadget(base, list));
        }
    }
}
