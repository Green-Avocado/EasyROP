package model.gadgets;

public class AddressGadget extends Gadget {
    private String address;

    // EFFECTS: creates a new AddressGadget
    public AddressGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + ".address + " + address + ")";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "AddressGadget (" + getBase() + ".address + " + address + ")";
    }

    // MODIFIES: this
    // EFFECTS: sets the address of the gadget
    public void setAddress(String address) {
        this.address = address;
    }

    // EFFECTS: returns the address of the gadget
    public String getAddress() {
        return address;
    }
}
