package model.gadgets;

// Represents a memory address where a given string can be found
public class StringGadget extends Gadget {
    private final String string;

    // EFFECTS: creates a new StringGadget
    public StringGadget(String base, String string) {
        super(base);
        this.string = string;
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(next(" + getBase() + ".search(b'" + string + "')))";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "StringGadget (" + string + ")";
    }
}
