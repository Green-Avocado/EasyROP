package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringGadgetTest {
    private StringGadget gadget0;
    private StringGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new StringGadget("exe", "cat flag.txt");
        gadget1 = new StringGadget("libc", "/bin/sh\\x00");
    }

    @Test
    void testGetScript() {
        assertEquals("pack(next(exe.search(b'cat flag.txt')))", gadget0.getScript());
        assertEquals("pack(next(libc.search(b'/bin/sh\\x00')))", gadget1.getScript());
    }

    @Test
    void testGetName() {
        assertEquals("StringGadget (cat flag.txt)", gadget0.getName());
        assertEquals("StringGadget (/bin/sh\\x00)", gadget1.getName());
    }

    @Test
    void testGetBase() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("libc", gadget1.getBase());
    }
}
