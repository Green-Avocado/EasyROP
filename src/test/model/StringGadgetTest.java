package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringGadgetTest {
    private StringGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new StringGadget();
    }

    @Test
    void getScriptTest() {
        gadget.setBase("exe");
        gadget.setString("cat flag.txt");
        assertEquals("next(exe.search(b'cat flag.txt'))", gadget.getScript());

        gadget.setBase("libc");
        gadget.setString("/bin/sh\\x00");
        assertEquals("next(libc.search(b'/bin/sh\\x00'))", gadget.getScript());
    }

    @Test
    void setStringTest() {
        gadget.setString("cat flag.txt");
        assertEquals("cat flag.txt", gadget.getString());

        gadget.setString("/bin/sh\"x00");
        assertEquals("/bin/sh\"x00", gadget.getString());
    }

    @Test
    void setBaseTest() {
        gadget.setBase("exe");
        assertEquals("exe", gadget.getBase());

        gadget.setBase("libc");
        assertEquals("libc", gadget.getBase());
    }
}
