package model;

import model.gadgets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PayloadTest {
    private Payload payload;

    @BeforeEach
    void runBefore() {
        payload = new Payload();
    }

    @Test
    void testFromList() {
        payload.fromList(Collections.singletonList("foo"));
        assertEquals("foo", payload.getName());

        payload.fromList(Collections.singletonList("BAR"));
        assertEquals("BAR", payload.getName());
    }

    @Test
    void testAdd() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
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
    void testRemove() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
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
    void testClear() {
        for (int i = 0; i < 3; i++) {
            payload.add(new RopChain(), payload.getLength());
        }

        payload.clear();
        assertEquals(0, payload.getLength());
    }

    @Test
    void testSetName() {
        payload.setName("testName");
        assertEquals("testName", payload.getName());

        payload.setName("nameTest");
        assertEquals("nameTest", payload.getName());
    }

    @Test
    void testGetRopChain() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.add(ropChain, payload.getLength());
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(ropChainList.get(i), payload.get(i));
        }

        // get out of bounds index
        assertNull(payload.get(payload.getLength()));
    }

    @Test
    void testGetRopChainList() {
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        assertEquals(ropChainList, payload.getList());

        for (int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            ropChainList.add(ropChain);
            payload.add(ropChain, payload.getLength());

            assertEquals(ropChainList, payload.getList());
        }
    }

    @Test
    void testGetLength() {
        assertEquals(0, payload.getLength());

        for (int i = 0; i < 3; i++) {
            RopChain ropChain = new RopChain();
            payload.add(ropChain, payload.getLength());

            assertEquals(i + 1, payload.getLength());
        }
    }

    @Test
    void testGetScript() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();
        ArrayList<String> scriptList = new ArrayList<>();
        scriptList.add("payload = ''");

        Padding padding = new Padding("8");
        gadgetList.add(padding);

        AddressGadget addressGadget = new AddressGadget("exe", "0xdeadbeef");
        gadgetList.add(addressGadget);

        InstructionsGadget instructionsGadget = new InstructionsGadget("libc", Arrays.asList("pop rdi", "ret"));
        gadgetList.add(instructionsGadget);

        StringGadget stringGadget = new StringGadget("exe", "/bin/sh\\x00");
        gadgetList.add(stringGadget);

        SymbolGadget symbolGadget = new SymbolGadget("libc", "sym", "puts");
        gadgetList.add(symbolGadget);

        payload.setName("payload");

        int i = 0;
        for (ExploitObject gadget : gadgetList) {
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

        assertEquals(String.join("\n", scriptList), payload.getScript());

    }

    @Test
    void testGetScriptEmptyPayload() {
        payload.setName("payload");
        assertEquals("payload = ''", payload.getScript());
    }

    @Test
    void testGetScriptEmptyRopChain() {
        RopChain ropChain = new RopChain();
        ropChain.setName("ropChain");

        payload.setName("payload");
        payload.add(ropChain, 0);

        assertEquals("payload = ''\nropChain = ''\npayload += ropChain", payload.getScript());
    }

    @Test
    void testGetExploitObjectType() {
        assertEquals(ExploitObjectType.PAYLOAD, payload.getExploitObjectType());
    }
}
