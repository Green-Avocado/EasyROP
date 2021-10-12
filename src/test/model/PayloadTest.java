package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PayloadTest {
    private Payload payload;

    @BeforeEach
    void runBefore() {
        payload = new Payload();
    }

    @Test
    void addRopChainTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
        }

        // add to out of bounds index
        assertFalse(payload.addRopChain(ropChainList.get(0), payload.getLength() + 1));
        assertEquals(0, payload.getLength());

        // add to empty
        assertTrue(payload.addRopChain(ropChainList.get(2), 0));
        assertEquals(1, payload.getLength());
        assertEquals(ropChainList.get(2), payload.getRopChain(0));

        // add to start of list
        assertTrue(payload.addRopChain(ropChainList.get(0), 0));
        assertEquals(2, payload.getLength());
        assertEquals(
                Arrays.asList(ropChainList.get(0), ropChainList.get(2)),
                payload.getRopChainList()
        );

        // add to middle of list
        assertTrue(payload.addRopChain(ropChainList.get(1), 1));
        assertEquals(3, payload.getLength());
        assertEquals(ropChainList.subList(0, ropChainList.size() - 1), payload.getRopChainList());

        // add to end of list
        assertTrue(payload.addRopChain(ropChainList.get(3), payload.getLength()));
        assertEquals(ropChainList.size(), payload.getLength());
        assertEquals(ropChainList, payload.getRopChainList());
    }

    @Test
    void removeRopChainTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.addRopChain(ropChain, payload.getLength());
        }

        // remove from out of bounds index
        assertFalse(payload.removeRopChain(payload.getLength()));
        assertEquals(3, payload.getLength());
        assertEquals(ropChainList, payload.getRopChainList());

        // remove from start of list
        assertTrue(payload.removeRopChain(0));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(ropChainList.subList(1, ropChainList.size()), payload.getRopChainList());

        payload.addRopChain(ropChainList.get(0), 0);

        // remove from middle of list
        assertTrue(payload.removeRopChain(payload.getLength() - 2));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(Arrays.asList(ropChainList.get(0), ropChainList.get(ropChainList.size() - 1)), payload.getRopChainList());

        payload.addRopChain(ropChainList.get(1), 1);

        // remove from end of list
        assertTrue(payload.removeRopChain(payload.getLength() - 1));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(ropChainList.subList(0, ropChainList.size() - 1), payload.getRopChainList());
    }

    @Test
    void payloadScriptTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();
        ArrayList<String> scriptList = new ArrayList<>();

        Padding padding = new Padding();
        padding.setLength(8);
        gadgetList.add(padding);

        Padding nullPadding = new Padding();
        nullPadding.setLength(0);
        gadgetList.add(nullPadding);

        AddressGadget addressGadget = new AddressGadget();
        addressGadget.setBase("exe");
        addressGadget.setAddress(0);
        gadgetList.add(addressGadget);

        InstructionsGadget instructionsGadget = new InstructionsGadget();
        instructionsGadget.setBase("exe");
        instructionsGadget.addInstruction("ret", 0);
        gadgetList.add(instructionsGadget);

        StringGadget stringGadget = new StringGadget();
        stringGadget.setBase("exe");
        stringGadget.setString("/bin/sh\\x00");
        gadgetList.add(stringGadget);

        SymbolGadget symbolGadget = new SymbolGadget();
        symbolGadget.setBase("exe");
        symbolGadget.setSymbol("puts");
        symbolGadget.setType("got");
        gadgetList.add(symbolGadget);

        for (Gadget gadget : gadgetList) {
            RopChain ropChain = new RopChain();
            ropChain.addGadget(gadget, 0);
            payload.addRopChain(ropChain, payload.getLength());
            if (!ropChain.ropScript().isEmpty()) {
                scriptList.add(ropChain.ropScript());
            }
        }

        assertEquals(
                String.join("\n", scriptList),
                payload.payloadScript()
        );
    }

    @Test
    void setIsAmd64Test() {
        Payload.setIsAmd64(false);
        assertFalse(Payload.getIsAmd64());

        Payload.setIsAmd64(true);
        assertTrue(Payload.getIsAmd64());
    }

    @Test
    void getRopChainTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.addRopChain(ropChain, payload.getLength());
        }

        for(int i = 0; i < 3; i++) {
            assertEquals(ropChainList.get(i), payload.getRopChain(i));
        }

        // get out of bounds index
        assertNull(payload.getRopChain(payload.getLength()));
    }

    @Test
    void getRopChainListTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        assertEquals(ropChainList, payload.getRopChainList());

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.addRopChain(ropChain, payload.getLength());

            assertEquals(ropChainList, payload.getRopChainList());
        }
    }

    @Test
    void getLengthTest() {
        assertEquals(0, payload.getLength());

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            payload.addRopChain(ropChain, payload.getLength());

            assertEquals(i + 1, payload.getLength());
        }
    }
}
