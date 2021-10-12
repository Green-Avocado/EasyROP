package model;

import java.util.LinkedList;

public class RopChain {
    private int padding = 0;
    private final LinkedList<Gadget> gadgetList;

    public RopChain() {
        gadgetList = new LinkedList<>();
    }

    public void addGadget(Gadget gadget, int index) {
        //TODO: STUB
    }

    public void removeGadget(int index) {
        //TODO: STUB
    }

    public void replaceGadget(Gadget gadget, int index) {
        //TODO: STUB
    }

    public Gadget getGadget(int index) {
        return null; //TODO: STUB
    }

    public LinkedList<Gadget> getGadgetList(int index) {
        return gadgetList;
    }

    public String ropScript() {
        return null; //TODO: STUB
    }

    public int getRopChainLength() {
        return gadgetList.size();
    }
}
