package model;

import model.gadgets.ExploitObject;

// Represents a set of ROP gadgets which perform a specific function in an exploit.
public class RopChain extends GadgetCollection {

    // EFFECTS: Creates a new RopChain with an empty list of gadgets
    public RopChain() {
        super();
    }

    // EFFECTS: Returns a string of Python commands to append a ExploitObject to a RopChain.
    protected String getScriptInternal(ExploitObject element) {
        return "\n" + getName() + " += " + element.getScript();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: Replaces the object at the specified index with the specified gadget,
    //          returns true if successful, otherwise returns false.
    public boolean set(ExploitObject gadget, int index) {
        if (index < getList().size()) {
            super.exploitObjectList.set(index, gadget);
            return true;
        } else {
            return false;
        }
    }
}
