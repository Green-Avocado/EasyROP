package model.gadgets;

import model.ExploitObjectType;

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
        setOffset(offset);
    }

    // REQUIRES: list.size() == 2
    // MODIFIES: this
    // EFFECTS: sets the base and offset of this object to the values in the list
    @Override
    public void fromList(List<String> list) {
        setBase(list.get(0));
        setOffset(list.get(1));
    }

    // EFFECTS: Returns a python command to produce the gadget.
    @Override
    public String getScript() {
        return "pack(" + getBase() + " + " + getOffset() + ")";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    @Override
    public String getName() {
        return "AddressGadget (" + getBase() + " + " + getOffset() + ")";
    }

    // MODIFIES: this
    // EFFECTS: Sets the offset of this object to the given string.
    public void setOffset(String offset) {
        this.offset = offset;
    }

    // EFFECTS: Returns the offset of this object.
    public String getOffset() {
        return offset;
    }

    // EFFECTS: Returns the type of this object.
    @Override
    public ExploitObjectType getExploitObjectType() {
        return ExploitObjectType.ADDRESS_GADGET;
    }
}
