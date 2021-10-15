package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionsGadgetTest {
    private InstructionsGadget gadget0;
    private InstructionsGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new InstructionsGadget("exe", Collections.singletonList("ret"));
        gadget1 = new InstructionsGadget("libc", Arrays.asList("pop rdi", "ret"));
    }

    @Test
    void testConstructor() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("libc", gadget1.getBase());

        InstructionsGadget gadget = new InstructionsGadget();
        assertNull(gadget.getBase());
        assertNull(gadget.getInstructions());
    }

    @Test
    void testFromList() {
        gadget0.fromList(Arrays.asList("aaa", "bbb", "ccc"));
        assertEquals("aaa", gadget0.getBase());
        assertEquals(Arrays.asList("bbb", "ccc"), gadget0.getInstructions());

        gadget1.fromList(Collections.singletonList("ABC"));
        assertEquals("ABC", gadget1.getBase());
        assertTrue(gadget1.getInstructions().isEmpty());
    }

    @Test
    void testGetScript() {
        assertEquals("rop_exe.find_gadget(['ret',])[0]", gadget0.getScript());
        assertEquals("rop_libc.find_gadget(['pop rdi','ret',])[0]", gadget1.getScript());
    }

    @Test
    void testGetName() {
        assertEquals("InstructionsGadget (ret;)", gadget0.getName());
        assertEquals("InstructionsGadget (pop rdi; ret;)", gadget1.getName());
    }

    @Test
    void testSetGetBase() {
        gadget0.setBase("aaa");
        assertEquals("aaa", gadget0.getBase());

        gadget1.setBase("ABC");
        assertEquals("ABC", gadget1.getBase());
    }

    @Test
    void testSetGetInstructions() {
        List<String> list;

        list = Arrays.asList("aaa", "bbb", "ccc");
        gadget0.setInstructions(list);
        assertEquals(list, gadget0.getInstructions());

        list = Collections.emptyList();
        gadget1.setInstructions(list);
        assertEquals(list, gadget1.getInstructions());
    }
}
