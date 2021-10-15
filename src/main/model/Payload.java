package model;

import model.gadgets.ExploitObject;

// Represents a collection of ROP chains used to exploit a program.
public class Payload extends GadgetCollection {

    // EFFECTS: Creates a new Payload with an empty list of RopChains.
    public Payload() {
        super();
    }

    // EFFECTS: Returns a string of Python commands to append a RopChain to a Payload.
    protected String getScriptInternal(ExploitObject element) {
        return "\n" + element.getScript() + "\n" + getName() + " += " + element.getName();
    }
}
