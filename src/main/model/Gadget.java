package model;

public abstract class Gadget {
    private String base;

    public Gadget() {
    }

    // EFFECTS sets the base of the gadget, returns true if successful, otherwise returns false
    public boolean setBase(String base) {
        this.base = base;
        return true;
    }

    // EFFECTS: returns the base of the gadget
    public String getBase() {
        return base;
    }
}
