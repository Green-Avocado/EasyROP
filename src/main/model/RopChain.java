package model;

import java.util.LinkedList;

public class RopChain {
    private final LinkedList<Gadget> gadgetList;

    // EFFECTS: creates a new RopChain with an empty list of gadgets
    public RopChain() {
        gadgetList = new LinkedList<>();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds gadget to the gadget list at the specified index, returns true if successful
    public boolean addGadget(Gadget gadget, int index) {
        if (index <= gadgetList.size()) {
            gadgetList.add(index, gadget);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces gadget at the specified index with the specified gadget, returns true if successful
    public boolean setGadget(Gadget gadget, int index) {
        if (index < gadgetList.size()) {
            gadgetList.set(index, gadget);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes gadget from specified index, returns true if successful
    public boolean removeGadget(int index) {
        if (index < gadgetList.size()) {
            gadgetList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns a string of Python commands to print the RopChain
    public String ropScript() {
        String script = "";

        for (Gadget gadget : gadgetList) {
            String gadgetScript = gadget.gadgetScript();

            if (!gadgetScript.isEmpty()) {
                if (!script.isEmpty()) {
                    script = script.concat("\n");
                }

                script = script.concat(gadget.gadgetScript());
            }
        }

        return script;
    }

    // REQUIRES index >= 0
    // EFFECTS: returns the gadget at the specified index or null if no such Gadget exists
    public Gadget getGadget(int index) {
        if (index < gadgetList.size()) {
            return gadgetList.get(index);
        } else {
            return null;
        }
    }

    // EFFECTS: returns the list of Gadgets
    public LinkedList<Gadget> getGadgetList() {
        return gadgetList;
    }

    // EFFECTS: returns the number of Gadgets in the RopChain
    public int getLength() {
        return gadgetList.size();
    }
}
