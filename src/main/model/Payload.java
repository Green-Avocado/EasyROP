package model;

import java.util.LinkedList;

public class Payload {
    private static boolean isAmd64 = true;

    private final LinkedList<RopChain> ropList;

    public Payload() {
        ropList = new LinkedList<>();
    }

    public void addRopChain(RopChain ropChain, int index) {
        //TODO: STUB
    }

    public void removeRopChain(int index) {
        //TODO: STUB
    }

    public RopChain getRopChain(int index) {
        return null; //TODO: STUB
    }

    public LinkedList<RopChain> getRopChainList() {
        return ropList;
    }

    public String payloadScript() {
        return null; //TODO: STUB
    }

    public int getPayloadLength() {
        return ropList.size(); //TODO: STUB
    }

    public static void setIsAmd64(boolean isAmd64) {
        Payload.isAmd64 = isAmd64;
    }

    public static boolean getIsAmd64() {
        return isAmd64;
    }
}
