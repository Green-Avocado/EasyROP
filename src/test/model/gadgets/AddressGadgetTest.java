package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddressGadgetTest {
    private AddressGadget gadget0;
    private AddressGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new AddressGadget("exe.address", "0xdeadbeef");
        gadget1 = new AddressGadget("libc.address", "0xcafebabe");
    }

    @Test
    void testConstructor() {
        assertEquals("exe.address", gadget0.getBase());
        assertEquals("0xdeadbeef", gadget0.getOffset());

        assertEquals("libc.address", gadget1.getBase());
        assertEquals("0xcafebabe", gadget1.getOffset());

        AddressGadget gadget = new AddressGadget();

        assertNull(gadget.getBase());
        assertNull(gadget.getOffset());
    }

    @Test
    void testFromList() {
        gadget0.fromList(Arrays.asList("bin.address", "0xcafebabe"));
        assertEquals("bin.address", gadget0.getBase());
        assertEquals("0xcafebabe", gadget0.getOffset());

        gadget1.fromList(Arrays.asList("VULN.address", "0xC0DED00D"));
        assertEquals("VULN.address", gadget1.getBase());
        assertEquals("0xC0DED00D", gadget1.getOffset());
    }

    @Test
    void testGetScript() {
        assertEquals("pack(exe.address + 0xdeadbeef)", gadget0.getScript());
        assertEquals("pack(libc.address + 0xcafebabe)", gadget1.getScript());
    }

    @Test
    void testGetName() {
        assertEquals("AddressGadget (exe.address + 0xdeadbeef)", gadget0.getName());
        assertEquals("AddressGadget (libc.address + 0xcafebabe)", gadget1.getName());
    }

    @Test
    void testSetGetBase() {
        gadget0.setBase("aaa");
        assertEquals("aaa", gadget0.getBase());

        gadget1.setBase("ABC");
        assertEquals("ABC", gadget1.getBase());
    }

    @Test
    void testSetGetOffset() {
        gadget0.setOffset("aaa");
        assertEquals("aaa", gadget0.getOffset());

        gadget1.setOffset("ABC");
        assertEquals("ABC", gadget1.getOffset());
    }
}
