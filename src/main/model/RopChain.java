package model;

import java.util.LinkedList;

public class RopChain {
    private int padding = 0;
    private final LinkedList<Gadget> gadgetList;

    // EFFECTS: creates a new RopChain with an empty list of gadgets
    public RopChain() {
        gadgetList = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds gadget to the gadget list at the specified index, returns true if successful
    public boolean addGadget(Gadget gadget, int index) {
        return false; //TODO: STUB
    }

    // MODIFIES: this
    // EFFECTS: removes gadget from specified index, returns true if successful
    public boolean removeGadget(int index) {
        return false; //TODO: STUB
    }

    // MODIFIES: this
    // EFFECTS: replaces gadget at the specified index with the specified gadget, returns true if successful
    public boolean replaceGadget(Gadget gadget, int index) {
        return false; //TODO: STUB
    }

    // EFFECTS: returns the gadget at the specified index or null if no such Gadget exists
    public Gadget getGadget(int index) {
        return null; //TODO: STUB
    }

    // EFFECTS: returns the list of Gadgets
    public LinkedList<Gadget> getGadgetList() {
        return gadgetList;
    }

    // EFFECTS: returns a string of Python commands to print the RopChain
    public String ropScript() {
        return null; //TODO: STUB
    }

    // EFFECTS: returns the number of Gadgets in the RopChain
    public int getRopChainLength() {
        return gadgetList.size();
    }
}
