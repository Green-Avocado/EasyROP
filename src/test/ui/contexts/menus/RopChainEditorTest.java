package ui.contexts.menus;

import model.Payload;
import model.RopChain;
import model.gadgets.Padding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RopChainEditorTest {
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        ropChainEditor = new RopChainEditor(new RopChain(), null);
    }

    @Test
    void testConstructor() {
        assertNull(ropChainEditor.getParentContext());

        RopChain ropChain = new RopChain();
        PayloadEditor payloadEditor = new PayloadEditor(new Payload());
        ropChainEditor = new RopChainEditor(ropChain, payloadEditor);

        assertEquals(ropChain, ropChainEditor.getCollection());
        assertEquals(payloadEditor, ropChainEditor.getParentContext());
    }

    @Test
    void testGetMenu() {
        assertEquals(
                Arrays.asList(
                        "[N]ew gadget",
                        "[m]ove gadget",
                        "[d]elete gadget",
                        "[p]rint ROPchain",
                        "[e]dit name",
                        "[q]uit"
                ),
                ropChainEditor.getMenu()
        );
    }

    @Test
    void testAdd() {
        assertEquals(GadgetCreator.class, ropChainEditor.add().getClass());
        assertEquals(ropChainEditor, ropChainEditor.add().getParentContext());
    }

    @Test
    void testOpen() {
        assertEquals(ropChainEditor, ropChainEditor.open());
    }

    @Test
    void testGetContextString() {
        // nested for loop tests different sized Payloads
        for (int i = 0; i < 16; i++) {
            ropChainEditor = new RopChainEditor(new RopChain(), null);
            ropChainEditor.getCollection().setName("testName");

            StringBuilder expectedString = new StringBuilder();
            expectedString.append("testName:\n");

            // adds j elements to the Payload
            for (int j = 0; j < i; j++) {
                Padding padding = new Padding(String.valueOf(j));
                ropChainEditor.getCollection().add(padding, j);
                expectedString.append("  ").append(padding.getName()).append("\n");
            }

            expectedString.append(String.join("  ", ropChainEditor.getMenu())).append("\n");

            assertEquals(expectedString.toString(), ropChainEditor.getContextString());
        }
    }

    @Test
    void testHandleInput() {
        assertEquals(
                ropChainEditor.handleInput("n").getContextString(),
                ropChainEditor.handleInput("N").getContextString()
        );

        // empty input
        assertEquals(
                ropChainEditor.handleInput("n").getContextString(),
                ropChainEditor.handleInput("").getContextString()
        );

        // invalid input
        assertEquals(ropChainEditor, ropChainEditor.handleInput("invalid input"));
    }

    @Test
    void testHandleInputN() {
        // case insensitivity
        assertEquals(
                ropChainEditor.handleInput("n").getContextString(),
                ropChainEditor.handleInput("N").getContextString()
        );

        // empty input
        assertEquals(
                ropChainEditor.handleInput("n").getContextString(),
                ropChainEditor.handleInput("").getContextString()
        );

        // invalid input
        assertEquals(ropChainEditor, ropChainEditor.handleInput("invalid input"));
    }

    @Test
    void testHandleInputO() {
    }

    @Test
    void testHandleInputM() {
    }

    @Test
    void testHandleInputD() {
    }

    @Test
    void testHandleInputP() {
    }

    @Test
    void testHandleInputE() {
    }

    @Test
    void testHandleInputQ() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testMove() {
    }

    @Test
    void testEditName() {
    }

    @Test
    void testPrint() {
    }
}
