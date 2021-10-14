package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionsGadgetTest {
    private InstructionsGadget gadget0;
    private InstructionsGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new InstructionsGadget("exe", Collections.singletonList("ret"));
        gadget1 = new InstructionsGadget("libc", Arrays.asList("pop rdi", "ret"));
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
    void testGetBase() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("libc", gadget1.getBase());
    }
}
