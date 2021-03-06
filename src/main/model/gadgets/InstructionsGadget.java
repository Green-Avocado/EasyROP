package model.gadgets;

import model.ExploitObjectType;

import java.util.Collections;
import java.util.List;

// Represents a memory address where a given set of assembly instructions can be found.
public class InstructionsGadget extends Gadget {
    private List<String> instructions;

    // EFFECTS: Creates a new empty InstructionsGadget
    public InstructionsGadget() {
        super();
    }

    // REQUIRES: !instructions.isEmpty()
    // EFFECTS: Creates a new InstructionsGadget with the specified base and instructions.
    public InstructionsGadget(String base, List<String> instructions) {
        super(base);
        setInstructions(instructions);
    }

    // REQUIRES: list.size() > 0
    // MODIFIES: this
    // EFFECTS: sets the base and offset of this object to the values in the list
    @Override
    public void fromList(List<String> list) {
        setBase(list.get(0));
        setInstructions(list.subList(1, list.size()));
    }

    // EFFECTS: Returns a python command to produce the gadget.
    @Override
    public String getScript() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("rop_");
        stringBuilder.append(getBase());
        stringBuilder.append(".find_gadget([");

        for (String instruction : getInstructions()) {
            stringBuilder.append("'");
            stringBuilder.append(instruction);
            stringBuilder.append("',");
        }

        stringBuilder.append("])[0]");

        return stringBuilder.toString();
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    @Override
    public String getName() {
        return "InstructionsGadget (" + String.join("; ", getInstructions()) + ";)";
    }

    // EFFECTS: Returns the list of instructions or null if none exists.
    public List<String> getInstructions() {
        if (instructions != null) {
            return Collections.unmodifiableList(instructions);
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the list of instructions to a given list of strings.
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    // EFFECTS: Returns the type of this object.
    @Override
    public ExploitObjectType getExploitObjectType() {
        return ExploitObjectType.INSTRUCTIONS_GADGET;
    }
}
