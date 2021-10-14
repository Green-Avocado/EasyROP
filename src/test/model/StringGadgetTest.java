package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringGadgetTest {
    private StringGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new StringGadget();
    }

    @Test
    void testGetScript() {
        gadget.setBase("exe");
        gadget.setString("cat flag.txt");
        assertEquals("pack(next(exe.search(b'cat flag.txt')))", gadget.getScript());

        gadget.setBase("libc");
        gadget.setString("/bin/sh\\x00");
        assertEquals("pack(next(libc.search(b'/bin/sh\\x00')))", gadget.getScript());
    }

    @Test
    void testGetName() {
        gadget.setString("cat flag.txt");
        assertEquals("StringGadget (cat flag.txt)", gadget.getName());

        gadget.setString("/bin/sh\\x00");
        assertEquals("StringGadget (/bin/sh\\x00)", gadget.getName());
    }

    @Test
    void testSetString() {
        gadget.setString("cat flag.txt");
        assertEquals("cat flag.txt", gadget.getString());

        gadget.setString("/bin/sh\"x00");
        assertEquals("/bin/sh\"x00", gadget.getString());
    }

    @Test
    void testSetBase() {
        gadget.setBase("exe");
        assertEquals("exe", gadget.getBase());

        gadget.setBase("libc");
        assertEquals("libc", gadget.getBase());
    }
}
