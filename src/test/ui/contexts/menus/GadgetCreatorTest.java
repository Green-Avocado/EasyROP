package ui.contexts.menus;

import model.RopChain;
import model.gadgets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.InstructionsGadgetCreator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GadgetCreatorTest {
    private GadgetCreator gadgetCreator;

    @BeforeEach
    void runBefore() {
        gadgetCreator = new GadgetCreator(new RopChainEditor(new RopChain(), null));
    }

    @Test
    void testConstructor() {
        RopChainEditor ropChainEditor = new RopChainEditor(null, null);
        gadgetCreator = new GadgetCreator(ropChainEditor);

        assertEquals(ropChainEditor, gadgetCreator.getParentContext());
    }

    @Test
    void testHandleInput() {
        // quit
        assertEquals(RopChainEditor.class, gadgetCreator.handleInput("q").getClass());

        // case insensitivity
        assertEquals(
                gadgetCreator.handleInput("p").getContextString(),
                gadgetCreator.handleInput("P").getContextString()
        );

        // empty input
        assertEquals(
                gadgetCreator.handleInput("p").getContextString(),
                gadgetCreator.handleInput("").getContextString()
        );

        // invalid input
        assertEquals(gadgetCreator, gadgetCreator.handleInput("invalid input"));
    }

    @Test
    void testHandleInputP() {
        ConsoleContext expectedContext = gadgetCreator.newPadding();
        ConsoleContext actualContext = gadgetCreator.handleInput("p");

        for (int i = 0; i < 2; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());
            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
        }

        assertEquals(expectedContext.getParentContext(), actualContext.getParentContext());
    }

    @Test
    void testHandleInputA() {
        ConsoleContext expectedContext = gadgetCreator.newAddressGadget();
        ConsoleContext actualContext = gadgetCreator.handleInput("a");

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());
            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
        }

        assertEquals(expectedContext.getParentContext(), actualContext.getParentContext());
    }

    @Test
    void testHandleInputI() {
        ConsoleContext expectedContext = new InstructionsGadgetCreator(gadgetCreator.getParentContext());
        ConsoleContext actualContext = gadgetCreator.handleInput("i");
        ArrayList<String> expectedInstructions = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());

            expectedContext = expectedContext.handleInput(String.valueOf(i));
            actualContext = actualContext.handleInput(String.valueOf(i));
            expectedInstructions.add(String.valueOf(i));
        }

        expectedInstructions.remove(0);

        assertEquals(expectedContext.getContextString(), actualContext.getContextString());

        for (int i = 0; i < 2; i++) {
            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
            assertEquals(expectedContext.getParentContext(), actualContext.getParentContext());
        }

        RopChain ropChain = (RopChain) ((RopChainEditor) gadgetCreator.getParentContext()).getCollection();
        InstructionsGadget gadget = (InstructionsGadget) ropChain.get(1);

        assertEquals("0", gadget.getBase());
        assertEquals(expectedInstructions, gadget.getInstructions());
    }

    @Test
    void testHandleInputS() {
        ConsoleContext expectedContext = gadgetCreator.newStringGadget();
        ConsoleContext actualContext = gadgetCreator.handleInput("s");

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());
            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
        }

        assertEquals(expectedContext.getParentContext(), actualContext.getParentContext());
    }

    @Test
    void testHandleInputSy() {
        ConsoleContext expectedContext = gadgetCreator.newSymbolGadget();
        ConsoleContext actualContext = gadgetCreator.handleInput("sy");

        for (int i = 0; i < 4; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());
            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
        }

        assertEquals(expectedContext.getParentContext(), actualContext.getParentContext());
    }

    @Test
    void testNewPadding() {
        ConsoleContext context = gadgetCreator.newPadding();

        context = context.handleInput("0x10");
        context.handleInput("");

        RopChain ropChain = (RopChain) ((RopChainEditor) gadgetCreator.getParentContext()).getCollection();
        Padding padding = (Padding) ropChain.get(0);

        assertEquals("0x10", padding.getLength());
    }

    @Test
    void testNewAddressGadget() {
        ConsoleContext context = gadgetCreator.newAddressGadget();

        for (int i = 0; i < 2; i++) {
            context = context.handleInput(String.valueOf(i));
        }
        context.handleInput("");

        RopChain ropChain = (RopChain) ((RopChainEditor) gadgetCreator.getParentContext()).getCollection();
        AddressGadget gadget = (AddressGadget) ropChain.get(0);

        assertEquals("0", gadget.getBase());
        assertEquals("1", gadget.getOffset());
    }

    @Test
    void testNewStringGadget() {
        ConsoleContext context = gadgetCreator.newStringGadget();

        for (int i = 0; i < 2; i++) {
            context = context.handleInput(String.valueOf(i));
        }
        context.handleInput("");

        RopChain ropChain = (RopChain) ((RopChainEditor) gadgetCreator.getParentContext()).getCollection();
        StringGadget gadget = (StringGadget) ropChain.get(0);

        assertEquals("0", gadget.getBase());
        assertEquals("1", gadget.getString());
    }

    @Test
    void testNewSymbolGadget() {
        ConsoleContext context = gadgetCreator.newSymbolGadget();

        for (int i = 0; i < 3; i++) {
            context = context.handleInput(String.valueOf(i));
        }
        context.handleInput("");

        RopChain ropChain = (RopChain) ((RopChainEditor) gadgetCreator.getParentContext()).getCollection();
        SymbolGadget gadget = (SymbolGadget) ropChain.get(0);

        assertEquals("0", gadget.getBase());
        assertEquals("1", gadget.getType());
        assertEquals("2", gadget.getSymbol());
    }

    @Test
    void testGetMenu() {
        assertEquals(
                Arrays.asList(
                        "[P]adding",
                        "[a]ddress gadget",
                        "[i]instructions gadget",
                        "[s]tring gadget",
                        "[sy]mbol gadget",
                        "[q]uit"),
                gadgetCreator.getMenu()
                );
    }
}
