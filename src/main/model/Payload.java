package model;

public class Payload extends GadgetCollection {

    // EFFECTS: creates a new Payload with an empty list of RopChains
    public Payload() {
        super();
    }

    // EFFECTS: returns a string of Python commands to append a RopChain to a Payload
    protected String getScriptInternal(ExploitObject element) {
        return "\n" + element.getScript() + "\n" + getName() + " += " + ((RopChain) element).getName();
    }
}
