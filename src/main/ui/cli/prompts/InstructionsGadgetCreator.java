package ui.cli.prompts;

import model.gadgets.InstructionsGadget;
import ui.cli.ConsoleContext;

import java.util.ArrayList;

// Represents a UI context that creates a new InstructionsGadget
public class InstructionsGadgetCreator extends PromptContext {
    private final ArrayList<String> instructions;
    private String base;

    // EFFECTS: Creates a new InstructionsGadgetCreator with the given parentContext.
    public InstructionsGadgetCreator(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
        this.instructions = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: If base is not set, returns a new InstructionsGadgetCreator with
    //          the same parentContext, the input as the base, and a new ArrayList as the instructions.
    //          If base is set and input is not "quit", adds input to instructions and returns a
    //          new InstructionsGadgetCreator with the same parentContext, base, and instructions.
    //          If base is set and input is "quit", returns the parentContext if instructions is empty,
    //          or returns an AddExploitObjectToIndex with the parentContext and a new InstructionsGadget
    //          with the base and list.
    @Override
    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            setPrompt("Instruction/quit");
            setDefaultResponse("quit");
            base = input;
            return this;
        } else if (!input.equalsIgnoreCase("quit")) {
            instructions.add(input);
            return this;
        } else if (instructions.isEmpty()) {
            return getParentContext();
        } else {
            return new AddExploitObjectToIndex(getParentContext(), new InstructionsGadget(base, instructions));
        }
    }
}
