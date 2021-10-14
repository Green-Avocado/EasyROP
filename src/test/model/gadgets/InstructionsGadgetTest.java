package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsGadgetTest {
    private InstructionsGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new InstructionsGadget();
    }

    @Test
    void testGetScript() {
        gadget.setBase("exe");
        gadget.add("ret", 0);

        assertEquals("rop_exe.find_gadget(['ret',])[0]", gadget.getScript());

        gadget.add("pop rdi", 0);

        assertEquals("rop_exe.find_gadget(['pop rdi','ret',])[0]", gadget.getScript());
    }

    @Test
    void testGetName() {
        for (int i = 0; i < 3; i++) {
            String instruction = "ret";
            gadget.add(instruction, 0);
            assertEquals("InstructionsGadget (" + (i + 1) + " instructions)", gadget.getName());
        }
    }

    @Test
    void testAddInstruction() {
        ArrayList<String> instructionList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String instruction = "ret";
            instructionList.add(instruction);
        }

        // add to out of bounds index
        assertFalse(gadget.add(instructionList.get(0), gadget.getLength() + 1));
        assertEquals(0, gadget.getLength());

        // add to empty
        assertTrue(gadget.add(instructionList.get(2), 0));
        assertEquals(1, gadget.getLength());
        assertEquals(instructionList.subList(2, 3), gadget.getInstructions());

        // add to start of list
        assertTrue(gadget.add(instructionList.get(0), 0));
        assertEquals(2, gadget.getLength());
        assertEquals(
                Arrays.asList(instructionList.get(0), instructionList.get(2)),
                gadget.getInstructions()
        );

        // add to middle of list
        assertTrue(gadget.add(instructionList.get(1), 1));
        assertEquals(3, gadget.getLength());
        assertEquals(instructionList.subList(0, instructionList.size() - 1), gadget.getInstructions());

        // add to end of list
        assertTrue(gadget.add(instructionList.get(3), gadget.getLength()));
        assertEquals(instructionList.size(), gadget.getLength());
        assertEquals(instructionList, gadget.getInstructions());
    }

    @Test
    void testSetInstruction() {
        ArrayList<String> instructionList = new ArrayList<>();
        ArrayList<String> newInstructionList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String instruction = "ret";
            instructionList.add(instruction);
            gadget.add(instruction, gadget.getLength());
        }

        for (int i = 0; i < 3; i++) {
            String instruction = "pop rdi";
            newInstructionList.add(instruction);
        }

        // replace out of bounds index
        assertFalse(gadget.set(newInstructionList.get(0), gadget.getLength()));
        assertEquals(instructionList, gadget.getInstructions());

        // replace first
        assertTrue(gadget.set(newInstructionList.get(0), 0));
        assertEquals(
                Arrays.asList(newInstructionList.get(0), instructionList.get(1), instructionList.get(2)),
                gadget.getInstructions()
        );

        // replace middle
        assertTrue(gadget.set(newInstructionList.get(1), 1));
        assertEquals(
                Arrays.asList(newInstructionList.get(0), newInstructionList.get(1), instructionList.get(2)),
                gadget.getInstructions()
        );

        // replace last
        assertTrue(gadget.set(
                newInstructionList.get(gadget.getLength() - 1),
                gadget.getLength() - 1
        ));
        assertEquals(
                newInstructionList,
                gadget.getInstructions()
        );
    }

    @Test
    void testRemoveInstruction() {
        ArrayList<String> instructionList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String instruction = "ret";
            instructionList.add(instruction);
            gadget.add(instruction, gadget.getLength());
        }

        // remove from out of bounds index
        assertFalse(gadget.remove(gadget.getLength()));
        assertEquals(3, gadget.getLength());
        assertEquals(instructionList, gadget.getInstructions());

        // remove from start of list
        assertTrue(gadget.remove(0));
        assertEquals(instructionList.size() - 1, gadget.getLength());
        assertEquals(instructionList.subList(1, instructionList.size()), gadget.getInstructions());

        gadget.add(instructionList.get(0), 0);

        // remove from middle of list
        assertTrue(gadget.remove(gadget.getLength() - 2));
        assertEquals(instructionList.size() - 1, gadget.getLength());
        assertEquals(Arrays.asList(instructionList.get(0), instructionList.get(instructionList.size() - 1)), gadget.getInstructions());

        gadget.add(instructionList.get(1), 1);

        // remove from end of list
        assertTrue(gadget.remove(gadget.getLength() - 1));
        assertEquals(instructionList.size() - 1, gadget.getLength());
        assertEquals(instructionList.subList(0, instructionList.size() - 1), gadget.getInstructions());
    }

    @Test
    void testGetInstructions() {
        ArrayList<String> instructionList = new ArrayList<>();

        assertEquals(instructionList, gadget.getInstructions());

        for (int i = 0; i < 3; i++) {
            String instruction = "ret";
            instructionList.add(instruction);
            gadget.add(instruction, gadget.getLength());

            assertEquals(instructionList, gadget.getInstructions());
        }
    }

    @Test
    void testGetLength() {
        for (int i = 0; i < 3; i++) {
            String instruction = "ret";
            gadget.add(instruction, 0);
            assertEquals(i + 1, gadget.getLength());
        }
    }

    @Test
    void testSetBase() {
        gadget.setBase("exe");
        assertEquals("exe", gadget.getBase());

        gadget.setBase("libc");
        assertEquals("libc", gadget.getBase());
    }
}
