package ui.contexts.prompts;

import model.gadgets.InstructionsGadget;
import ui.contexts.ConsoleContext;

import java.util.ArrayList;

// Represents a UI context that creates a new InstructionsGadget
public class InstructionsGadgetCreator extends PromptContext {
    private String base;
    private ArrayList<String> instructions;

    // EFFECTS: Creates a new InstructionsGadgetCreator with the given parentContext.
    public InstructionsGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    // EFFECTS: Creates a new InstructionsGadgetCreator with the given parentContext, base, and list of instructions.
    public InstructionsGadgetCreator(ConsoleContext parentContext, String base, ArrayList<String> instructions) {
        super(parentContext, "Instruction/quit", "quit");

        this.base = base;
        this.instructions = instructions;
    }

    // MODIFIES: this
    // EFFECTS: If base is not set, returns a new InstructionsGadgetCreator with
    //          the same parentContext, the input as the base, and a new ArrayList as the instructions.
    //          If base is set and input is not "quit", adds input to instructions and returns a
    //          new InstructionsGadgetCreator with the same parentContext, base, and instructions.
    //          If base is set and input is "quit", returns the parentContext if instructions is empty,
    //          or returns an AddExploitObjectToIndex with the parentContext and a new InstructionsGadget
    //          with the base and list.
    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new InstructionsGadgetCreator(getParentContext(), input, new ArrayList<>());
        } else if (!input.equalsIgnoreCase("quit")) {
            instructions.add(input);
            return new InstructionsGadgetCreator(getParentContext(), base, instructions);
        } else if (instructions.isEmpty()) {
            return getParentContext();
        } else {
            return new AddExploitObjectToIndex(getParentContext(), new InstructionsGadget(base, instructions));
        }
    }
}
