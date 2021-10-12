package model;

public class Payload extends GadgetCollection {
    private static boolean isAmd64 = true;

    // EFFECTS: creates a new Payload with an empty list of RopChains
    public Payload() {
        super();
        setName("payload");
    }

    // EFFECTS: returns a string of Python commands to append a RopChain to a Payload
    protected String getScriptInternal(ExploitElement element) {
        return "\n" + element.getScript() + "\n" + getName() + " += " + ((RopChain) element).getName();
    }

    // MODIFIES: this
    // EFFECTS: sets whether isAmd64 is true or false
    public static void setIsAmd64(boolean isAmd64) {
        Payload.isAmd64 = isAmd64;
    }

    // EFFECTS: returns true if the payload is to be formatted for Amd64 and false for i386
    public static boolean getIsAmd64() {
        return isAmd64;
    }
}
