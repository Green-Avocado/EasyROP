package model.gadgets;

import java.util.List;

// Represents a memory address where a given set of assembly instructions can be found
public class InstructionsGadget extends Gadget {
    private final List<String> instructions;

    // REQUIRES: !instructions.isEmpty()
    // EFFECTS: creates a new InstructionsGadget with the specified base and instructions
    public InstructionsGadget(String base, List<String> instructions) {
        super(base);
        this.instructions = instructions;
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("rop_");
        stringBuilder.append(getBase());
        stringBuilder.append(".find_gadget([");

        for (String instruction : instructions) {
            stringBuilder.append("'");
            stringBuilder.append(instruction);
            stringBuilder.append("',");
        }

        stringBuilder.append("])[0]");

        return stringBuilder.toString();
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "InstructionsGadget (" + String.join("; ", instructions) + ";)";
    }
}
