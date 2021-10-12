package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SymbolGadgetTest {
    private SymbolGadget gadget;

    @BeforeEach
    void runBefore() {
        gadget = new SymbolGadget();
    }

    @Test
    void getScriptTest() {
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
    void setSymbolTest() {
        gadget.setSymbol("puts");
        assertEquals("puts", gadget.getSymbol());

        gadget.setSymbol("system");
        assertEquals("system", gadget.getSymbol());
    }

    @Test
    void setTypeTest() {
        gadget.setType("got");
        assertEquals("got", gadget.getType());

        gadget.setType("sym");
        assertEquals("sym", gadget.getType());
    }

    @Test
    void setBaseTest() {
        gadget.setBase("exe");
        assertEquals("exe", gadget.getBase());

        gadget.setBase("libc");
        assertEquals("libc", gadget.getBase());
    }
}
