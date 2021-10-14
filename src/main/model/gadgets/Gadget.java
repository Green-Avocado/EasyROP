package model.gadgets;

// Represents a memory address relative to a given base address
public abstract class Gadget implements ExploitObject {
    private final String base;

    // EFFECTS: creates a new gadget with the specified base
    public Gadget(String base) {
        this.base = base;
    }

    // EFFECTS: returns the base of the gadget
    public String getBase() {
        return base;
    }
}
