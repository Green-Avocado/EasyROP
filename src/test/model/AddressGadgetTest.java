package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressGadgetTest {
    private AddressGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new AddressGadget();
    }

    @Test
    void testGetScript() {
        gadget.setBase("exe");
        gadget.setAddress("0xdeadbeef");
        assertEquals("pack(exe.address + 0xdeadbeef)", gadget.getScript());

        gadget.setBase("libc");
        gadget.setAddress("0xcafebabe");
        assertEquals("pack(libc.address + 0xcafebabe)", gadget.getScript());
    }

    @Test
    void testGetName() {
        gadget.setBase("exe");
        gadget.setAddress("0xdeadbeef");
        assertEquals("AddressGadget (exe.address + 0xdeadbeef)", gadget.getName());

        gadget.setBase("libc");
        gadget.setAddress("0xcafebabe");
        assertEquals("AddressGadget (libc.address + 0xcafebabe)", gadget.getName());
    }

    @Test
    void testSetAddress() {
        for (int i = 0; i <= 256; i += 16) {
            gadget.setAddress(Integer.toHexString(i));
            assertEquals(Integer.toHexString(i), gadget.getAddress());
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
