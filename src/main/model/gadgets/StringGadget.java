package model.gadgets;

import model.ExploitObjectType;

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
        setString(string);
    }

    // REQUIRES: list.size() == 2
    // MODIFIES: this
    // EFFECTS: sets the base and string of this object to the values in the list
    @Override
    public void fromList(List<String> list) {
        setBase(list.get(0));
        setString(list.get(1));
    }

    // EFFECTS: Returns a python command to produce the gadget.
    @Override
    public String getScript() {
        return "pack(next(" + getBase() + ".search(b'" + getString() + "')))";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    @Override
    public String getName() {
        return "StringGadget (" + getString() + ")";
    }

    // MODIFIES: this
    // EFFECTS: Sets the string of this object to the specified string.
    public void setString(String string) {
        this.string = string;
    }

    // EFFECTS: Returns the string of this object.
    public String getString() {
        return string;
    }

    // EFFECTS: Returns the type of this object.
    @Override
    public ExploitObjectType getExploitObjectType() {
        return ExploitObjectType.STRING_GADGET;
    }
}
