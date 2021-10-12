package model;

public class Padding implements ExploitElement {
    private int length;

    // EFFECTS: creates a new padding element
    public Padding() {
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        StringBuilder paddingString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            paddingString.append("a");
        }

        return paddingString.toString();
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
