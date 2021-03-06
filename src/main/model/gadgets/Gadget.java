package model.gadgets;

import model.ExploitObject;

// Represents a memory address relative to a given base address.
public abstract class Gadget implements ExploitObject {
    private String base;

    // EFFECTS: Creates a new gadget
    public Gadget() {
    }

    // EFFECTS: Creates a new gadget with the specified base.
    public Gadget(String base) {
        setBase(base);
    }

    // EFFECTS: Returns the base of the gadget.
    public String getBase() {
        return base;
    }

    // EFFECTS: Returns the base of the gadget.
    public void setBase(String base) {
        this.base = base;
    }
}
