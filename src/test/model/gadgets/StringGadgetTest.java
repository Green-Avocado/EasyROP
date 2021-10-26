package model.gadgets;

import model.ExploitObjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StringGadgetTest {
    private StringGadget gadget0;
    private StringGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new StringGadget("exe", "cat flag.txt");
        gadget1 = new StringGadget("libc", "/bin/sh\\x00");
    }

    @Test
    void testConstructor() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("cat flag.txt", gadget0.getString());

        assertEquals("libc", gadget1.getBase());
        assertEquals("/bin/sh\\x00", gadget1.getString());

        StringGadget gadget = new StringGadget();

        assertNull(gadget.getBase());
        assertNull(gadget.getString());
    }

    @Test
    void testFromList() {
        gadget0.fromList(Arrays.asList("bin", "whoami"));
        assertEquals("bin", gadget0.getBase());
        assertEquals("whoami", gadget0.getString());

        gadget1.fromList(Arrays.asList("VULN", "FLAG{"));
        assertEquals("VULN", gadget1.getBase());
        assertEquals("FLAG{", gadget1.getString());
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
    void testSetGetBase() {
        gadget0.setBase("aaa");
        assertEquals("aaa", gadget0.getBase());

        gadget1.setBase("ABC");
        assertEquals("ABC", gadget1.getBase());
    }

    @Test
    void testSetGetString() {
        gadget0.setString("aaa");
        assertEquals("aaa", gadget0.getString());

        gadget1.setString("ABC");
        assertEquals("ABC", gadget1.getString());
    }

    @Test
    void testGetExploitObjectType() {
        assertEquals(ExploitObjectType.STRING_GADGET, gadget0.getExploitObjectType());
    }
}
