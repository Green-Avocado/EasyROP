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

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
        }

        // add to out of bounds index
        assertFalse(ropChain.addGadget(gadgetList.get(0), ropChain.getRopChainLength() + 1));
        assertEquals(0, ropChain.getRopChainLength());

        // add to empty
        assertTrue(ropChain.addGadget(gadgetList.get(0), 0));
        assertEquals(1, ropChain.getRopChainLength());
        assertEquals(gadgetList.get(0), ropChain.getGadget(0));

        // add to end of list
        assertTrue(ropChain.addGadget(gadgetList.get(2), ropChain.getRopChainLength()));
        assertEquals(2, ropChain.getRopChainLength());
        assertEquals(
                Arrays.asList(gadgetList.get(0), gadgetList.get(2)),
                ropChain.getGadgetList()
        );

        // add to middle of list
        assertTrue(ropChain.addGadget(gadgetList.get(1), 1));
        assertEquals(gadgetList.size(), ropChain.getRopChainLength());
        assertEquals(gadgetList, ropChain.getGadgetList());
    }

    @Test
    void setGadgetTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();
        ArrayList<Gadget> newGadgetList = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getRopChainLength());
        }

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            newGadgetList.add(gadget);
        }

        // replace out of bounds index
        assertFalse(ropChain.setGadget(newGadgetList.get(0), ropChain.getRopChainLength()));
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
                newGadgetList.get(ropChain.getRopChainLength() - 1),
                ropChain.getRopChainLength() - 1
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
            ropChain.addGadget(gadget, ropChain.getRopChainLength());
        }

        // remove from out of bounds index
        assertFalse(ropChain.removeGadget(ropChain.getRopChainLength()));
        assertEquals(3, ropChain.getRopChainLength());
        assertEquals(gadgetList, ropChain.getGadgetList());

        // remove from start of list
        assertTrue(ropChain.removeGadget(0));
        assertEquals(gadgetList.size() - 1, ropChain.getRopChainLength());
        assertEquals(gadgetList.subList(1, gadgetList.size()), ropChain.getGadgetList());

        ropChain.addGadget(gadgetList.get(0), 0);

        // remove from middle of list
        assertTrue(ropChain.removeGadget(ropChain.getRopChainLength() - 2));
        assertEquals(gadgetList.size() - 1, ropChain.getRopChainLength());
        assertEquals(Arrays.asList(gadgetList.get(0), gadgetList.get(gadgetList.size() - 1)), ropChain.getGadgetList());

        ropChain.addGadget(gadgetList.get(1), 1);

        // remove from end of list
        assertTrue(ropChain.removeGadget(ropChain.getRopChainLength() - 1));
        assertEquals(gadgetList.size() - 1, ropChain.getRopChainLength());
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

        ropChain.addGadget(padding, ropChain.getRopChainLength());
        ropChain.addGadget(nullPadding, ropChain.getRopChainLength());
        ropChain.addGadget(addressGadget, ropChain.getRopChainLength());
        ropChain.addGadget(instructionsGadget, ropChain.getRopChainLength());
        ropChain.addGadget(stringGadget, ropChain.getRopChainLength());
        ropChain.addGadget(symbolGadget, ropChain.getRopChainLength());

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
            ropChain.addGadget(gadget, ropChain.getRopChainLength());
        }

        for(int i = 0; i < 3; i++) {
            assertEquals(gadgetList.get(i), ropChain.getGadget(i));
        }

        // get out of bounds index
        assertNull(ropChain.getGadget(ropChain.getRopChainLength()));
    }

    @Test
    void getGadgetListTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        assertEquals(gadgetList, ropChain.getGadgetList());

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getRopChainLength());

            assertEquals(gadgetList, ropChain.getGadgetList());
        }
    }

    @Test
    void getRopChainLengthTest() {
        ArrayList<Gadget> gadgetList = new ArrayList<>();

        assertEquals(0, ropChain.getRopChainLength());

        for(int i = 0; i < 3; i++) {
            Gadget gadget = new AddressGadget();
            gadgetList.add(gadget);
            ropChain.addGadget(gadget, ropChain.getRopChainLength());

            assertEquals(i + 1, ropChain.getRopChainLength());
        }
    }
}
