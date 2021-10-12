package model;

public class AddressGadget extends Gadget implements ExploitElement {
    private int address;

    // EFFECTS: creates a new AddressGadget
    public AddressGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "dummy script"; //TODO: STUB
    }

    // MODIFIES: this
    // EFFECTS: sets the address of the gadget
    public void setAddress(int address) {
        this.address = address;
    }

    // EFFECTS: returns the address of the gadget
    public int getAddress() {
        return address;
    }
}
