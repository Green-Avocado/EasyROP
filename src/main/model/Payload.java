package model;

import java.util.LinkedList;

public class Payload {
    private static boolean isAmd64 = true;

    private final LinkedList<RopChain> ropChainList;

    // EFFECTS: creates a new Payload with an empty list of RopChains
    public Payload() {
        ropChainList = new LinkedList<>();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds a RopChain to the Payload at the specified index, returns true if successful
    public boolean addRopChain(RopChain ropChain, int index) {
        if (index <= ropChainList.size()) {
            ropChainList.add(index, ropChain);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: removes a RopChain from the Payload at the specified index, returns true if successful
    public boolean removeRopChain(int index) {
        if (index < ropChainList.size()) {
            ropChainList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: return
    public String payloadScript() {
        String script = "";

        for (RopChain ropChain : ropChainList) {
            String ropScript = ropChain.ropScript();

            if (!ropScript.isEmpty()) {
                if (!script.isEmpty()) {
                    script = script.concat("\n");
                }

                script = script.concat(ropScript);
            }
        }

        return script;
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

    // REQUIRES index >= 0
    // EFFECTS: returns the RopChain at the specified index
    public RopChain getRopChain(int index) {
        if (index < ropChainList.size()) {
            return ropChainList.get(index);
        } else {
            return null;
        }
    }

    // EFFECTS: returns the list of RopChains
    public LinkedList<RopChain> getRopChainList() {
        return ropChainList;
    }

    // EFFECTS: returns the number of RopChains in the Payload
    public int getLength() {
        return ropChainList.size();
    }
}
