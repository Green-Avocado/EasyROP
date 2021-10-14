package model.gadgets;

public class StringGadget extends Gadget {
    private String string;

    // EFFECTS: creates a new StringGadget
    public StringGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(next(" + getBase() + ".search(b'" + string + "')))";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "StringGadget (" + string + ")";
    }

    // MODIFIES: this
    // EFFECTS: sets the string to search for
    public void setString(String string) {
        this.string = string;
    }

    // EFFECTS: returns the string to search for
    public String getString() {
        return string;
    }
}
