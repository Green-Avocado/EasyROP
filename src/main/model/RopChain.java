package model;

public class RopChain extends GadgetCollection {
    // EFFECTS: creates a new RopChain with an empty list of gadgets
    public RopChain() {
        super();
        setName("ropChain");
    }

    // EFFECTS: returns a string of Python commands to append a ExploitElement to a RopChain
    protected String getScriptInternal(ExploitElement element) {
        return "\n" + getName() + " += " + element.getScript();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the ExploitElement at the specified index with the specified ExploitElement,
    //          returns true if successful, otherwise returns false
    public boolean set(ExploitElement gadget, int index) {
        if (index < getList().size()) {
            getList().set(index, gadget);
            return true;
        } else {
            return false;
        }
    }
}
