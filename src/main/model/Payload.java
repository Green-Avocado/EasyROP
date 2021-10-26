package model;

// Represents a collection of ROP chains used to exploit a program.
public class Payload extends GadgetCollection {

    // EFFECTS: Creates a new Payload with an empty list of RopChains.
    public Payload() {
        super();
    }

    // EFFECTS: Returns a string of Python commands to append a RopChain to a Payload.
    @Override
    String getScriptInternal(ExploitObject element) {
        return "\n" + element.getScript() + "\n" + getName() + " += " + element.getName();
    }

    // EFFECTS: Returns the type of this object.
    @Override
    public ExploitObjectType getExploitObjectType() {
        return ExploitObjectType.PAYLOAD;
    }
}
