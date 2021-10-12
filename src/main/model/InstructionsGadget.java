package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InstructionsGadget extends Gadget implements ExploitElement {
    private LinkedList<String> instructions;

    // EFFECTS: creates a new InstructionsGadget
    public InstructionsGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "dummy script"; //TODO: STUB
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds an instruction to the list at the specified index,
    //          returns true if successful, otherwise returns false
    public boolean addInstruction(String instruction, int index) {
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
    public boolean setInstruction(String instruction, int index) {
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
    public boolean removeInstruction(int index) {
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
}
