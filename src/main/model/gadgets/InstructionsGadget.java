package model.gadgets;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InstructionsGadget extends Gadget {
    private final LinkedList<String> instructions;

    // EFFECTS: creates a new InstructionsGadget
    public InstructionsGadget() {
        super();

        instructions = new LinkedList<>();
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
        return "InstructionsGadget (" + instructions.size() + " instructions)";
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds an instruction to the list at the specified index,
    //          returns true if successful, otherwise returns false
    public boolean add(String instruction, int index) {
        if (index <= instructions.size()) {
            instructions.add(index, instruction);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the instruction at the specified index with the specified instruction,
    //          returns true if successful, otherwise returns false
    public boolean set(String instruction, int index) {
        if (index < instructions.size()) {
            instructions.set(index, instruction);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: removes the instruction at the specified index,
    //          returns true if successful, otherwise returns false
    public boolean remove(int index) {
        if (index < instructions.size()) {
            instructions.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns an immutable list of instructions
    public List<String> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }

    // EFFECTS: returns an immutable list of instructions
    public int getLength() {
        return instructions.size();
    }
}