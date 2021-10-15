package model.gadgets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SymbolGadgetTest {
    private SymbolGadget gadget0;
    private SymbolGadget gadget1;

    @BeforeEach
    void runBefore() {
        gadget0 = new SymbolGadget("exe", "got", "puts");
        gadget1 = new SymbolGadget("libc", "sym", "system");
    }

    @Test
    void testConstructor() {
        assertEquals("exe", gadget0.getBase());
        assertEquals("got", gadget0.getType());
        assertEquals("puts", gadget0.getSymbol());

        assertEquals("libc", gadget1.getBase());
        assertEquals("sym", gadget1.getType());
        assertEquals("system", gadget1.getSymbol());

        SymbolGadget gadget = new SymbolGadget();

        assertNull(gadget.getBase());
        assertNull(gadget.getType());
        assertNull(gadget.getSymbol());
    }

    @Test
    void testFromList() {
        gadget0.fromList(Arrays.asList("bin", "plt", "printf"));
        assertEquals("bin", gadget0.getBase());
        assertEquals("plt", gadget0.getType());
        assertEquals("printf", gadget0.getSymbol());

        gadget1.fromList(Arrays.asList("VULN", "symbol", "__libc_start_main"));
        assertEquals("VULN", gadget1.getBase());
        assertEquals("symbol", gadget1.getType());
        assertEquals("__libc_start_main", gadget1.getSymbol());
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
    void testSetGetBase() {
        gadget0.setBase("aaa");
        assertEquals("aaa", gadget0.getBase());

        gadget1.setBase("ABC");
        assertEquals("ABC", gadget1.getBase());
    }

    @Test
    void testSetGetType() {
        gadget0.setType("aaa");
        assertEquals("aaa", gadget0.getType());

        gadget1.setType("ABC");
        assertEquals("ABC", gadget1.getType());
    }

    @Test
    void testSetGetSymbol() {
        gadget0.setSymbol("aaa");
        assertEquals("aaa", gadget0.getSymbol());

        gadget1.setSymbol("ABC");
        assertEquals("ABC", gadget1.getSymbol());
    }
}
