package model.gadgets;

import model.ExploitObject;
import model.ExploitObjectType;

import java.util.List;

// Represents an arbitrary number of characters used to overflow a character buffer.
public class Padding implements ExploitObject {
    private String length;

    // EFFECTS: Creates a new padding element
    public Padding() {
    }

    // EFFECTS: Creates a new padding element
    public Padding(String length) {
        setLength(length);
    }

    // REQUIRES: list.size() == 0
    // MODIFIES: this
    // EFFECTS: sets the padding length to the element in this list
    @Override
    public void fromList(List<String> list) {
        setLength(list.get(0));
    }

    // EFFECTS: Returns a python command to produce the gadget.
    @Override
    public String getScript() {
        return "b'a' * " + getLength();
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    @Override
    public String getName() {
        return "Padding (" + getLength() + ")";
    }

    // EFFECTS: Returns the length of this object.
    public String getLength() {
        return length;
    }

    // MODIFIES: this
    // EFFECTS: Sets the length of this object to the given length.
    public void setLength(String length) {
        this.length = length;
    }

    // EFFECTS: Returns the type of this object.
    @Override
    public ExploitObjectType getExploitObjectType() {
        return ExploitObjectType.PADDING;
    }
}
