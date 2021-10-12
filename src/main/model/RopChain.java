package model;

public class RopChain extends GadgetCollection {
    // EFFECTS: creates a new RopChain with an empty list of gadgets
    public RopChain() {
        super();
    }

    // EFFECTS: returns a string of Python commands to append a ExploitObject to a RopChain
    protected String getScriptInternal(ExploitObject element) {
        return "\n" + getName() + " += " + element.getScript();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the object at the specified index with the specified gadget,
    //          returns true if successful, otherwise returns false
    public boolean set(ExploitObject gadget, int index) {
        if (index < getList().size()) {
            getList().set(index, gadget);
            return true;
        } else {
            return false;
        }
    }
}
