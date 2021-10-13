package model;

public class Padding implements ExploitObject {
    private int length;

    // EFFECTS: creates a new padding element
    public Padding() {}

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        if (length > 0) {
            return "b'a' * " + length;
        } else {
            return "";
        }
    }

    // REQUIRES: length >= 0
    // EFFECTS: sets the length of padding
    public void setLength(int length) {
        this.length = length;
    }

    // EFFECTS: returns the length of padding
    public int getLength() {
        return length;
    }
}
