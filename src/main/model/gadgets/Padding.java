package model.gadgets;

public class Padding implements ExploitObject {
    private final int length;

    // EFFECTS: creates a new padding element
    public Padding(int length) {
        this.length = length;
    }

    // REQUIRES: length > 0
    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "b'a' * " + length;
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "Padding (" + length + ")";
    }

    // EFFECTS: returns the length of padding
    public int getLength() {
        return length;
    }
}
