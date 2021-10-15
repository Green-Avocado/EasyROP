package model.gadgets;

import java.util.List;

// Represents a memory address with a given offset from a base address.
public class AddressGadget extends Gadget {
    private String offset;

    // EFFECTS: Creates a new AddressGadget
    public AddressGadget() {
        super();
    }

    // EFFECTS: Creates a new AddressGadget with the specified base and address.
    public AddressGadget(String base, String offset) {
        super(base);
        this.offset = offset;
    }

    // REQUIRES: list.size() == 2
    // EFFECTS: sets the base and offset of this object to the values in the list
    // MODIFIES: this
    public void fromList(List<String> list) {
        setBase(list.get(0));
        offset = list.get(1);
    }

    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "pack(" + getBase() + ".address + " + offset + ")";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "AddressGadget (" + getBase() + ".address + " + offset + ")";
    }
}
