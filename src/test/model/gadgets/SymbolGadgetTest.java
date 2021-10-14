package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SymbolGadgetTest {
    private SymbolGadget gadget0;
    private SymbolGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new SymbolGadget("exe", "got", "puts");
        gadget1 = new SymbolGadget("libc", "sym", "system");
    }

    @Test
    void testGetScript() {
        assertEquals("pack(exe.got['puts'])", gadget0.getScript());
        assertEquals("pack(libc.sym['system'])", gadget1.getScript());
    }

    @Test
    void testGetName() {
        assertEquals("SymbolGadget (exe.got.puts)", gadget0.getName());
        assertEquals("SymbolGadget (libc.sym.system)", gadget1.getName());
    }

    @Test
    void testGetBase() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("libc", gadget1.getBase());
    }
}
