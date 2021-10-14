package ui.contexts.prompts;

import model.gadgets.InstructionsGadget;
import ui.contexts.ConsoleContext;

import java.util.ArrayList;

public class NewInstructionsGadgetContext extends PromptContext {
    private String base;
    private ArrayList<String> list;

    public NewInstructionsGadgetContext(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public NewInstructionsGadgetContext(ConsoleContext parentContext, String base, ArrayList<String> list) {
        super(parentContext, "Instruction/quit", "quit");

        this.base = base;
        this.list = list;
    }

    ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new NewInstructionsGadgetContext(getParentContext(), input, new ArrayList<>());
        } else if (!input.equalsIgnoreCase("quit")) {
            list.add(input.toLowerCase());
            return new NewInstructionsGadgetContext(getParentContext(), input, list);
        } else if (list.isEmpty()) {
            return getParentContext();
        } else {
            return new NewIndexContext(getParentContext(), new InstructionsGadget(base, list));
        }
    }
}
