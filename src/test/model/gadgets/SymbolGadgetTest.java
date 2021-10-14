package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SymbolGadgetTest {
    private SymbolGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new SymbolGadget();
    }

    @Test
    void testGetScript() {
        gadget.setBase("exe");
        gadget.setType("got");
        gadget.setSymbol("puts");

        assertEquals("pack(exe.got['puts'])", gadget.getScript());

        gadget.setBase("libc");
        gadget.setType("sym");
        gadget.setSymbol("system");

        assertEquals("pack(libc.sym['system'])", gadget.getScript());
    }

    @Test
    void testGetName() {
        gadget.setBase("exe");
        gadget.setType("got");
        gadget.setSymbol("puts");
        assertEquals("SymbolGadget (exe.got.puts)", gadget.getName());

        gadget.setBase("libc");
        gadget.setType("sym");
        gadget.setSymbol("system");
        assertEquals("SymbolGadget (libc.sym.system)", gadget.getName());
    }

    @Test
    void testSetSymbol() {
        gadget.setSymbol("puts");
        assertEquals("puts", gadget.getSymbol());

        gadget.setSymbol("system");
        assertEquals("system", gadget.getSymbol());
    }

    @Test
    void testSetType() {
        gadget.setType("got");
        assertEquals("got", gadget.getType());

        gadget.setType("sym");
        assertEquals("sym", gadget.getType());
    }

    @Test
    void testSetBase() {
        gadget.setBase("exe");
        assertEquals("exe", gadget.getBase());

        gadget.setBase("libc");
        assertEquals("libc", gadget.getBase());
    }
}
