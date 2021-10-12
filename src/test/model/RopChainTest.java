package model;

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
    void addGadgetTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
        }

        // add to out of bounds index
        assertFalse(ropChain.addGadget(gadgetList.get(0), ropChain.getLength() + 1));
        assertEquals(0, ropChain.getLength());

        // add to empty
        assertTrue(ropChain.addGadget(gadgetList.get(2), 0));
        assertEquals(1, ropChain.getLength());
        assertEquals(gadgetList.get(2), ropChain.getGadget(0));

        // add to start of list
        assertTrue(ropChain.addGadget(gadgetList.get(0), 0));
        assertEquals(2, ropChain.getLength());
        assertEquals(
                Arrays.asList(gadgetList.get(0), gadgetList.get(2)),
                ropChain.getGadgetList()
        );

        // add to middle of list
        assertTrue(ropChain.addGadget(gadgetList.get(1), 1));
        assertEquals(3, ropChain.getLength());
        assertEquals(gadgetList.subList(0, gadgetList.size() - 1), ropChain.getGadgetList());

        // add to end of list
        assertTrue(ropChain.addGadget(gadgetList.get(3), ropChain.getLength()));
        assertEquals(gadgetList.size(), ropChain.getLength());
        assertEquals(gadgetList, ropChain.getGadgetList());
    }

    @Test
    void setGadgetTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();
        ArrayList<Gadget> newGadgetList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getLength());
        }

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            newGadgetList.add(gadget);
        }

        // replace out of bounds index
        assertFalse(ropChain.setGadget(newGadgetList.get(0), ropChain.getLength()));
        assertEquals(gadgetList, ropChain.getGadgetList());

        // replace first
        assertTrue(ropChain.setGadget(newGadgetList.get(0), 0));
        assertEquals(
                Arrays.asList(newGadgetList.get(0), gadgetList.get(1), gadgetList.get(2)),
                ropChain.getGadgetList()
        );

        // replace middle
        assertTrue(ropChain.setGadget(newGadgetList.get(1), 1));
        assertEquals(
                Arrays.asList(newGadgetList.get(0), newGadgetList.get(1), gadgetList.get(2)),
                ropChain.getGadgetList()
        );

        // replace last
        assertTrue(ropChain.setGadget(
                newGadgetList.get(ropChain.getLength() - 1),
                ropChain.getLength() - 1
        ));
        assertEquals(
                newGadgetList,
                ropChain.getGadgetList()
        );
    }

    @Test
    void removeGadgetTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getLength());
        }

        // remove from out of bounds index
        assertFalse(ropChain.removeGadget(ropChain.getLength()));
        assertEquals(3, ropChain.getLength());
        assertEquals(gadgetList, ropChain.getGadgetList());

        // remove from start of list
        assertTrue(ropChain.removeGadget(0));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(gadgetList.subList(1, gadgetList.size()), ropChain.getGadgetList());

        ropChain.addGadget(gadgetList.get(0), 0);

        // remove from middle of list
        assertTrue(ropChain.removeGadget(ropChain.getLength() - 2));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(Arrays.asList(gadgetList.get(0), gadgetList.get(gadgetList.size() - 1)), ropChain.getGadgetList());

        ropChain.addGadget(gadgetList.get(1), 1);

        // remove from end of list
        assertTrue(ropChain.removeGadget(ropChain.getLength() - 1));
        assertEquals(gadgetList.size() - 1, ropChain.getLength());
        assertEquals(gadgetList.subList(0, gadgetList.size() - 1), ropChain.getGadgetList());
    }

    @Test
    void ropScriptTest() {
        Padding padding = new Padding();
        padding.setLength(8);

        Padding nullPadding = new Padding();
        nullPadding.setLength(0);

        AddressGadget addressGadget = new AddressGadget();
        addressGadget.setBase("exe");
        addressGadget.setAddress(0);

        InstructionsGadget instructionsGadget = new InstructionsGadget();
        instructionsGadget.setBase("exe");
        instructionsGadget.addInstruction("ret", 0);

        StringGadget stringGadget = new StringGadget();
        stringGadget.setBase("exe");
        stringGadget.setString("/bin/sh\\x00");

        SymbolGadget symbolGadget = new SymbolGadget();
        symbolGadget.setBase("exe");
        symbolGadget.setSymbol("puts");
        symbolGadget.setType("got");

        ropChain.addGadget(padding, ropChain.getLength());
        ropChain.addGadget(nullPadding, ropChain.getLength());
        ropChain.addGadget(addressGadget, ropChain.getLength());
        ropChain.addGadget(instructionsGadget, ropChain.getLength());
        ropChain.addGadget(stringGadget, ropChain.getLength());
        ropChain.addGadget(symbolGadget, ropChain.getLength());

        assertEquals(
                String.join(
                        "\n",
                        padding.gadgetScript(),
                        addressGadget.gadgetScript(),
                        instructionsGadget.gadgetScript(),
                        stringGadget.gadgetScript(),
                        symbolGadget.gadgetScript()
                ),
                ropChain.ropScript()
        );
    }

    @Test
    void getGadgetTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getLength());
        }

        for(int i = 0; i < 3; i++) {
            assertEquals(gadgetList.get(i), ropChain.getGadget(i));
        }

        // get out of bounds index
        assertNull(ropChain.getGadget(ropChain.getLength()));
    }

    @Test
    void getGadgetListTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        assertEquals(gadgetList, ropChain.getGadgetList());

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getLength());

            assertEquals(gadgetList, ropChain.getGadgetList());
        }
    }

    @Test
    void getLengthTest() {
        assertEquals(0, ropChain.getLength());

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            ropChain.addGadget(gadget, ropChain.getLength());

            assertEquals(i + 1, ropChain.getLength());
        }
    }
}
