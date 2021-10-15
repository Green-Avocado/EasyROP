package model.gadgets;

import java.util.List;

// Represents an arbitrary number of characters used to overflow a character buffer.
public class Padding implements ExploitObject {
    private String length;

    // EFFECTS: Creates a new padding element
    public Padding() {
    }

    // EFFECTS: Creates a new padding element
    public Padding(String length) {
        this.length = length;
    }

    // REQUIRES: list.size() == 0
    // EFFECTS: sets the padding length to the element in this list
    // MODIFIES: this
    public void fromList(List<String> list) {
        length = list.get(0);
    }

    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "b'a' * " + length;
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "Padding (" + length + ")";
    }
}
