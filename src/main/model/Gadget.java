package model;

public abstract class Gadget implements ExploitObject {
    private String base;

    // EFFECTS: creates a new gadget
    public Gadget() {
    }

    // MODIFIES: this
    // EFFECTS sets the base of the gadget
    public void setBase(String base) {
        this.base = base;
    }

    // EFFECTS: returns the base of the gadget
    public String getBase() {
        return base;
    }
}
