package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressGadgetTest {
    private AddressGadget gadget0;
    private AddressGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new AddressGadget("exe", "0xdeadbeef");
        gadget1 = new AddressGadget("libc", "0xcafebabe");
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
    void testGetBase() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("libc", gadget1.getBase());
    }
}
