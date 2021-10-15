package model.gadgets;

import java.util.List;

// Represents a memory address where a given set of assembly instructions can be found.
public class InstructionsGadget extends Gadget {
    private List<String> instructions;

    // REQUIRES: !instructions.isEmpty()
    // EFFECTS: Creates a new InstructionsGadget with the specified base and instructions.
    public InstructionsGadget(String base, List<String> instructions) {
        super(base);
        this.instructions = instructions;
    }

    // REQUIRES: list.size() > 0
    // EFFECTS: sets the base and offset of this object to the values in the list
    // MODIFIES: this
    public void fromList(List<String> list) {
        setBase(list.get(0));
        instructions = list.subList(0, list.size());
    }

    // EFFECTS: Returns a python command to produce the gadget.
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

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "InstructionsGadget (" + String.join("; ", instructions) + ";)";
    }
}
