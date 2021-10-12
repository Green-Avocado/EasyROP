package model;

import java.util.LinkedList;

public class InstructionsGadget implements Gadget {
    private String base;
    private LinkedList<String> instructions;

    public InstructionsGadget() {
    }

    public String getScript() {
        return "dummy script"; //TODO: STUB
    }

    public boolean addInstruction(String instruction, int index) {
        return false; //TODO: STUB
    }

    public boolean setInstruction(String instruction, int index) {
        return false; //TODO: STUB
    }

    public boolean removeInstruction(int index) {
        return false; //TODO: STUB
    }

    public LinkedList<String> getInstructions() {
        return null; //TODO: STUB
    }

    public boolean setBase(String base) {
        return false; //TODO: STUB
    }

    public String getBase() {
        return null; //TODO: STUB
    }
}
