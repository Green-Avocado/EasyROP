package model;

public class StringGadget extends Gadget implements ExploitElement {
    private String string;

    // EFFECTS: creates a new StringGadget
    public StringGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "dummy script"; //TODO: STUB
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
