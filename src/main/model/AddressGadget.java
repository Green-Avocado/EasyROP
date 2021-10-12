package model;

public class AddressGadget extends Gadget implements ExploitElement {
    private String address;

    // EFFECTS: creates a new AddressGadget
    public AddressGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + ".address + " + address + ")";
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
