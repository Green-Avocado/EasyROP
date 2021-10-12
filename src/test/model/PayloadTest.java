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
    void addTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
        }

        // add to out of bounds index
        assertFalse(payload.add(ropChainList.get(0), payload.getLength() + 1));
        assertEquals(0, payload.getLength());

        // add to empty
        assertTrue(payload.add(ropChainList.get(2), 0));
        assertEquals(1, payload.getLength());
        assertEquals(ropChainList.get(2), payload.get(0));

        // add to start of list
        assertTrue(payload.add(ropChainList.get(0), 0));
        assertEquals(2, payload.getLength());
        assertEquals(
                Arrays.asList(ropChainList.get(0), ropChainList.get(2)),
                payload.getList()
        );

        // add to middle of list
        assertTrue(payload.add(ropChainList.get(1), 1));
        assertEquals(3, payload.getLength());
        assertEquals(ropChainList.subList(0, ropChainList.size() - 1), payload.getList());

        // add to end of list
        assertTrue(payload.add(ropChainList.get(3), payload.getLength()));
        assertEquals(ropChainList.size(), payload.getLength());
        assertEquals(ropChainList, payload.getList());
    }

    @Test
    void removeTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.add(ropChain, payload.getLength());
        }

        // remove from out of bounds index
        assertFalse(payload.remove(payload.getLength()));
        assertEquals(3, payload.getLength());
        assertEquals(ropChainList, payload.getList());

        // remove from start of list
        assertTrue(payload.remove(0));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(ropChainList.subList(1, ropChainList.size()), payload.getList());

        payload.add(ropChainList.get(0), 0);

        // remove from middle of list
        assertTrue(payload.remove(payload.getLength() - 2));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(Arrays.asList(ropChainList.get(0), ropChainList.get(ropChainList.size() - 1)), payload.getList());

        payload.add(ropChainList.get(1), 1);

        // remove from end of list
        assertTrue(payload.remove(payload.getLength() - 1));
        assertEquals(ropChainList.size() - 1, payload.getLength());
        assertEquals(ropChainList.subList(0, ropChainList.size() - 1), payload.getList());
    }

    @Test
    void setNameTest() {
        payload.setName("abcdefgh");
        assertEquals("abcdefgh", payload.getName());

        payload.setName("hgfedcba");
        assertEquals("hgfedcba", payload.getName());
    }

    @Test
    void getRopChainTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.add(ropChain, payload.getLength());
        }

        for(int i = 0; i < 3; i++) {
            assertEquals(ropChainList.get(i), payload.get(i));
        }

        // get out of bounds index
        assertNull(payload.get(payload.getLength()));
    }

    @Test
    void getRopChainListTest() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        assertEquals(ropChainList, payload.getList());

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.add(ropChain, payload.getLength());

            assertEquals(ropChainList, payload.getList());
        }
    }

    @Test
    void getLengthTest() {
        assertEquals(0, payload.getLength());

        for(int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            payload.add(ropChain, payload.getLength());

            assertEquals(i + 1, payload.getLength());
        }
    }

    @Test
    void getScriptTest() {
        ArrayList<ExploitElement> gadgetList = new ArrayList<>();
        ArrayList<String> scriptList = new ArrayList<>();
        scriptList.add("payload = ''");

        Padding padding = new Padding();
        padding.setLength(8);
        gadgetList.add(padding);

        Padding nullPadding = new Padding();
        nullPadding.setLength(0);
        gadgetList.add(nullPadding);

        AddressGadget addressGadget = new AddressGadget();
        addressGadget.setBase("exe");
        addressGadget.setAddress("0xdeadbeef");
        gadgetList.add(addressGadget);

        InstructionsGadget instructionsGadget = new InstructionsGadget();
        instructionsGadget.setBase("exe");
        instructionsGadget.add("ret", 0);
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

        int i = 0;
        for (ExploitElement gadget : gadgetList) {
            RopChain ropChain = new RopChain();

            for (int j = 0; j <= i; j++) {
                ropChain.add(gadget, 0);
            }

            payload.add(ropChain, payload.getLength());

            if (!ropChain.getScript().isEmpty()) {
                scriptList.add(ropChain.getScript());
                scriptList.add("payload += " + ropChain.getName());
            }

            i++;
        }

        assertEquals(
                String.join("\n", scriptList),
                payload.getScript()
        );

        RopChain ropChain = new RopChain();
        payload = new Payload();

        ropChain.add(nullPadding, ropChain.getLength());
        payload.add(ropChain, payload.getLength());

        assertEquals("", payload.getScript());
    }
}
