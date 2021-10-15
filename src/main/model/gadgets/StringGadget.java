package model.gadgets;

import java.util.List;

// Represents a memory address where a given string can be found.
public class StringGadget extends Gadget {
    private String string;

    // EFFECTS: Creates a new StringGadget.
    public StringGadget() {
        super();
    }

    // EFFECTS: Creates a new StringGadget with a specified base and string
    public StringGadget(String base, String string) {
        super(base);
        this.string = string;
    }

    // REQUIRES: list.size() == 2
    // EFFECTS: sets the base and string of this object to the values in the list
    // MODIFIES: this
    public void fromList(List<String> list) {
        setBase(list.get(0));
        string = list.get(1);
    }

    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "pack(next(" + getBase() + ".search(b'" + string + "')))";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "StringGadget (" + string + ")";
    }
}
