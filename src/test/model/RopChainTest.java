package model;

import model.gadgets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RopChainTest {
    private RopChain ropChain;

    @BeforeEach
    void runBefore() {
        ropChain = new RopChain();
    }

    @Test
    void testAdd() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            gadgetList.add(gadget);
        }

        // add to out of bounds index
        assertFalse(ropChain.add(gadgetList.get(0), ropChain.getLength() + 1));
        assertEquals(0, ropChain.getLength());

        // add to empty
        assertTrue(ropChain.add(gadgetList.get(2), 0));
        assertEquals(1, ropChain.getLength());
        assertEquals(gadgetList.get(2), ropChain.get(0));

        // add to start of list
        assertTrue(ropChain.add(gadgetList.get(0), 0));
        assertEquals(2, ropChain.getLength());
        assertEquals(
                Arrays.asList(gadgetList.get(0), gadgetList.get(2)),
                ropChain.getList()
        );

        // add to middle of list
        assertTrue(ropChain.add(gadgetList.get(1), 1));
        assertEquals(3, ropChain.getLength());
        assertEquals(gadgetList.subList(0, gadgetList.size() - 1), ropChain.getList());

        // add to end of list
        assertTrue(ropChain.add(gadgetList.get(3), ropChain.getLength()));
        assertEquals(gadgetList.size(), ropChain.getLength());
        assertEquals(gadgetList, ropChain.getList());
    }

    @Test
    void testRemove() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            gadgetList.add(gadget);
            ropChain.add(gadget, ropChain.getLength());
        }

        // remove from out of bounds index
        assertFalse(ropChain.remove(ropChain.getLength()));
        assertEquals(3, ropChain.getLength());
        assertEquals(gadgetList, ropChain.getList());

        // remove from start of list
        assertTrue(ropChain.remove(0));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(gadgetList.subList(1, gadgetList.size()), ropChain.getList());

        ropChain.add(gadgetList.get(0), 0);

        // remove from middle of list
        assertTrue(ropChain.remove(ropChain.getLength() - 2));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(Arrays.asList(gadgetList.get(0), gadgetList.get(gadgetList.size() - 1)), ropChain.getList());

        ropChain.add(gadgetList.get(1), 1);

        // remove from end of list
        assertTrue(ropChain.remove(ropChain.getLength() - 1));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(gadgetList.subList(0, gadgetList.size() - 1), ropChain.getList());
    }

    @Test
    void testSet() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();
        ArrayList<ExploitObject> newGadgetList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            gadgetList.add(gadget);
            ropChain.add(gadget, ropChain.getLength());
        }

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            newGadgetList.add(gadget);
        }

        // replace out of bounds index
        assertFalse(ropChain.set(newGadgetList.get(0), ropChain.getLength()));
        assertEquals(gadgetList, ropChain.getList());

        // replace first
        assertTrue(ropChain.set(newGadgetList.get(0), 0));
        assertEquals(
                Arrays.asList(newGadgetList.get(0), gadgetList.get(1), gadgetList.get(2)),
                ropChain.getList()
        );

        // replace middle
        assertTrue(ropChain.set(newGadgetList.get(1), 1));
        assertEquals(
                Arrays.asList(newGadgetList.get(0), newGadgetList.get(1), gadgetList.get(2)),
                ropChain.getList()
        );

        // replace last
        assertTrue(ropChain.set(
                newGadgetList.get(ropChain.getLength() - 1),
                ropChain.getLength() - 1
        ));
        assertEquals(
                newGadgetList,
                ropChain.getList()
        );
    }

    @Test
    void testGet() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            gadgetList.add(gadget);
            ropChain.add(gadget, ropChain.getLength());
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(gadgetList.get(i), ropChain.get(i));
        }

        // get out of bounds index
        assertNull(ropChain.get(ropChain.getLength()));
    }

    @Test
    void testSetName() {
        ropChain.setName("testName");
        assertEquals("testName", ropChain.getName());

        ropChain.setName("nameTest");
        assertEquals("nameTest", ropChain.getName());
    }

    @Test
    void testGetList() {
        ArrayList<ExploitObject> gadgetList = new ArrayList<>();

        assertEquals(gadgetList, ropChain.getList());

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            gadgetList.add(gadget);
            ropChain.add(gadget, ropChain.getLength());

            assertEquals(gadgetList, ropChain.getList());
        }
    }

    @Test
    void testGetLength() {
        assertEquals(0, ropChain.getLength());

        for (int i = 0; i < 3; i++) {
            ExploitObject gadget = new AddressGadget("exe", "0xdeadbeef");
            ropChain.add(gadget, ropChain.getLength());

            assertEquals(i + 1, ropChain.getLength());
        }
    }

    @Test
    void testGetScript() {
        Padding padding = new Padding(8);
        AddressGadget addressGadget = new AddressGadget("exe", "0xdeadbeef");
        InstructionsGadget instructionsGadget = new InstructionsGadget("libc", Arrays.asList("pop rdi", "ret"));
        StringGadget stringGadget = new StringGadget("exe", "/bin/sh\\x00");
        SymbolGadget symbolGadget = new SymbolGadget("libc", "sym", "puts");

        ropChain.add(new Padding(8), ropChain.getLength());
        ropChain.add(new AddressGadget("exe", "0xdeadbeef"), ropChain.getLength());
        ropChain.add(new InstructionsGadget("libc", Arrays.asList("pop rdi", "ret")), ropChain.getLength());
        ropChain.add(new StringGadget("exe", "/bin/sh\\x00"), ropChain.getLength());
        ropChain.add(new SymbolGadget("libc", "sym", "puts"), ropChain.getLength());

        ropChain.setName("ropChain");

        assertEquals(
                String.join(
                        "\n",
                        "ropChain = ''",
                        "ropChain += " + padding.getScript(),
                        "ropChain += " + addressGadget.getScript(),
                        "ropChain += " + instructionsGadget.getScript(),
                        "ropChain += " + stringGadget.getScript(),
                        "ropChain += " + symbolGadget.getScript()
                ),
                ropChain.getScript()
        );
    }

    @Test
    void testGetScriptEmpty() {
        ropChain.setName("ropChain");
        assertEquals("ropChain = ''", ropChain.getScript());

        ropChain.setName("ROPchain0");
        assertEquals("ROPchain0 = ''", ropChain.getScript());
    }
}
