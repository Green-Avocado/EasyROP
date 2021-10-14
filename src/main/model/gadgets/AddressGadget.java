package model.gadgets;

public class AddressGadget extends Gadget {
    private final String address;

    // EFFECTS: creates a new AddressGadget with the specified base and address
    public AddressGadget(String base, String address) {
        super(base);
        this.address = address;
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + ".address + " + address + ")";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "AddressGadget (" + getBase() + ".address + " + address + ")";
    }
}
