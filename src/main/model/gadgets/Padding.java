package model.gadgets;

// Represents an arbitrary number of characters used to overflow a character buffer.
public class Padding implements ExploitObject {
    private final int length;

    // EFFECTS: Creates a new padding element
    public Padding(int length) {
        this.length = length;
    }

    // REQUIRES: length > 0
    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "b'a' * " + length;
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "Padding (" + length + ")";
    }
}
