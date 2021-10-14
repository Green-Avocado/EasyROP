package model.gadgets;

// Represents a memory address with a given offset from a base address
public class AddressGadget extends Gadget {
    private final String offset;

    // EFFECTS: creates a new AddressGadget with the specified base and address
    public AddressGadget(String base, String offset) {
        super(base);
        this.offset = offset;
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + ".address + " + offset + ")";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "AddressGadget (" + getBase() + ".address + " + offset + ")";
    }
}
