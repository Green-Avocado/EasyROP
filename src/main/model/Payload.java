package model;

import java.util.LinkedList;

public class Payload {
    private static boolean isAmd64 = true;

    private final LinkedList<Gadget> gadgetChain;

    public Payload() {
        gadgetChain = new LinkedList<>();
    }

    public void addGadget(Gadget gadget) {
        //TODO: STUB
    }

    public void removeGadget(int index) {
        //TODO: STUB
    }

    public LinkedList<Gadget> viewPayload() {
        return null; //TODO: STUB
    }

    public String outputScript() {
        return null; //TODO: STUB
    }

    public static void setIsAmd64(boolean isAmd64) {
        Payload.isAmd64 = isAmd64;
    }
}
