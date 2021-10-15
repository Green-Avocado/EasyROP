package model.gadgets;

// Represents an arbitrary number of characters used to overflow a character buffer.
public class Padding implements ExploitObject {
    private final String length;

    // EFFECTS: Creates a new padding element
    public Padding(String length) {
        this.length = length;
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
