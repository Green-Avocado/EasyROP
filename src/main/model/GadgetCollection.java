package model;

import java.util.LinkedList;

public abstract class GadgetCollection {
    private final LinkedList<Gadget> payloadElementList;

    // EFFECTS: creates a new GadgetCollection with an empty list of gadgets
    public GadgetCollection() {
        payloadElementList = new LinkedList<>();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds gadget to the gadget list at the specified index, returns true if successful
    public boolean add(Gadget gadget, int index) {
        if (index <= payloadElementList.size()) {
            payloadElementList.add(index, gadget);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the Gadget at the specified index with the specified gadget, returns true if successful
    public boolean set(Gadget gadget, int index) {
        if (index < payloadElementList.size()) {
            payloadElementList.set(index, gadget);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the Gadget at specified index, returns true if successful
    public boolean remove(int index) {
        if (index < payloadElementList.size()) {
            payloadElementList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns a string of Python commands to produce the script
    public String getScript() {
        String script = "";

        for (Gadget gadget : payloadElementList) {
            String gadgetScript = gadget.getScript();

            if (!gadgetScript.isEmpty()) {
                if (!script.isEmpty()) {
                    script = script.concat("\n");
                }

                script = script.concat(gadgetScript);
            }
        }

        return script;
    }

    // REQUIRES index >= 0
    // EFFECTS: returns the gadget at the specified index or null if no such Gadget exists
    public Gadget get(int index) {
        if (index < payloadElementList.size()) {
            return payloadElementList.get(index);
        } else {
            return null;
        }
    }

    // EFFECTS: returns the list of Gadgets
    public LinkedList<Gadget> getList() {
        return payloadElementList;
    }

    // EFFECTS: returns the number of Gadgets in the list
    public int getLength() {
        return payloadElementList.size();
    }
}
