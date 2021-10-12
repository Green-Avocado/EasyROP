package model;

public class Padding implements ExploitElement {
    private int length;

    public Padding() {
    }

    public String getScript() {
        String paddingString = "";

        for (int i = 0; i < length; i++) {
            paddingString = paddingString.concat("a");
        }

        return paddingString;
    }

    public boolean setLength(int length) {
        this.length = length;
        return true;
    }

    public int getLength() {
        return 0; //TODO: STUB
    }
}
