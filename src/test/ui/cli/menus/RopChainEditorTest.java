package ui.cli.menus;

import model.Payload;
import model.RopChain;
import model.gadgets.Padding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ConsoleContext;
import ui.cli.TextViewer;
import ui.cli.prompts.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RopChainEditorTest {
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        ropChainEditor = new RopChainEditor(new PayloadEditor(new Payload()), new RopChain());
    }

    @Test
    void testConstructor() {
        RopChain ropChain = new RopChain();
        PayloadEditor payloadEditor = new PayloadEditor(new Payload());
        ropChainEditor = new RopChainEditor(payloadEditor, ropChain);

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
                        "[s]ave",
                        "[l]oad",
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
        // nested for loop tests different sized RopChains
        for (int i = 0; i < 16; i++) {
            ropChainEditor = new RopChainEditor(null, new RopChain());
            ropChainEditor.getCollection().setName("testName");

            StringBuilder expectedString = new StringBuilder();
            expectedString.append("testName:\n");

            // adds j elements to the RopChain
            for (int j = 0; j < i; j++) {
                Padding padding = new Padding(String.valueOf(j));
                ropChainEditor.getCollection().add(padding, j);
                expectedString.append(j).append(": ").append(padding.getName()).append("\n");
            }

            expectedString.append(String.join("  ", ropChainEditor.getMenu())).append("\n");

            assertEquals(expectedString.toString(), ropChainEditor.getContextString());
        }
    }

    @Test
    void testHandleInput() {
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
    void testHandleInputN() {
        assertEquals(ropChainEditor.add().getContextString(), ropChainEditor.handleInput("n").getContextString());
        assertEquals(ropChainEditor.add().getClass(), ropChainEditor.handleInput("n").getClass());
    }

    @Test
    void testHandleInputO() {
        for (int i = 0; i < 16; i++) {
            // add new gadget
            ropChainEditor.add().handleInput("").handleInput(String.valueOf(i)).handleInput("");

            // test context string is expected
            assertEquals(ropChainEditor.open().getContextString(), ropChainEditor.handleInput("o").getContextString());
            assertEquals(
                    ropChainEditor.open().handleInput("").getContextString(),
                    ropChainEditor.handleInput("o").handleInput("").getContextString()
            );
        }

        assertEquals(ropChainEditor.open().getClass(), ropChainEditor.handleInput("o").getClass());
    }

    @Test
    void testHandleInputM() {
        ropChainEditor.add().handleInput("").handleInput("");

        for (int i = 0; i < 16; i++) {
            // add new gadget
            ropChainEditor.add().handleInput("").handleInput(String.valueOf(i)).handleInput("");

            // test context string is expected
            ConsoleContext expectedContext = ropChainEditor.move().handleInput("");
            String expectedContextString = expectedContext.getContextString();
            expectedContext.handleInput("");

            ConsoleContext actualContext = ropChainEditor.move().handleInput("");
            String actualContextString = actualContext.getContextString();
            actualContext.handleInput("");

            assertEquals(ropChainEditor.move().getContextString(), ropChainEditor.handleInput("m").getContextString());
            assertEquals(expectedContextString, actualContextString);
        }

        assertEquals(ropChainEditor.move().getClass(), ropChainEditor.handleInput("m").getClass());
    }

    @Test
    void testHandleInputD() {
        assertEquals(ropChainEditor.delete().getContextString(), ropChainEditor.handleInput("d").getContextString());
        assertEquals(ropChainEditor.delete().getClass(), ropChainEditor.handleInput("d").getClass());
    }

    @Test
    void testHandleInputP() {
        for (int i = 0; i < 16; i++) {
            ropChainEditor.getCollection().setName("testName" + i);
            ropChainEditor.add().handleInput("").handleInput(String.valueOf(i)).handleInput("");
            assertEquals(ropChainEditor.print().getContextString(), ropChainEditor.handleInput("p").getContextString());
        }

        assertEquals(ropChainEditor.print().getClass(), ropChainEditor.handleInput("p").getClass());
    }

    @Test
    void testHandleInputE() {
        assertEquals(ropChainEditor.editName().getContextString(), ropChainEditor.handleInput("e").getContextString());
        assertEquals(ropChainEditor.editName().getClass(), ropChainEditor.handleInput("e").getClass());
    }

    @Test
    void testHandleInputS() {
        assertEquals(ropChainEditor.save().getContextString(), ropChainEditor.handleInput("s").getContextString());
        assertEquals(ropChainEditor.save().getClass(), ropChainEditor.handleInput("s").getClass());
    }

    @Test
    void testHandleInputL() {
        assertEquals(ropChainEditor.load().getContextString(), ropChainEditor.handleInput("l").getContextString());
        assertEquals(ropChainEditor.load().getClass(), ropChainEditor.handleInput("l").getClass());
    }

    @Test
    void testHandleInputQ() {
        assertEquals(ropChainEditor.getParentContext(), ropChainEditor.handleInput("q"));
    }

    @Test
    void testDelete() {
        // length <= 0
        assertEquals(ropChainEditor, ropChainEditor.delete());

        // length > 0
        ropChainEditor.add().handleInput("").handleInput("").handleInput("");
        assertEquals(ExploitObjectRemover.class, ropChainEditor.delete().getClass());
        assertEquals(ropChainEditor, ropChainEditor.delete().getParentContext());
    }

    @Test
    void testMove() {
        // length <= 1
        assertEquals(ropChainEditor, ropChainEditor.move());

        ropChainEditor.add().handleInput("").handleInput("").handleInput("");
        assertEquals(ropChainEditor, ropChainEditor.move());

        // length > 1
        ropChainEditor.add().handleInput("").handleInput("").handleInput("");
        assertEquals(ExploitObjectMover.class, ropChainEditor.move().getClass());
        assertEquals(ropChainEditor, ropChainEditor.move().getParentContext());
    }

    @Test
    void testEditName() {
        ropChainEditor.editName().handleInput("testName");
        assertEquals("testName", ropChainEditor.getCollection().getName());

        assertEquals(SetCollectionName.class, ropChainEditor.editName().getClass());
        assertEquals(ropChainEditor, ropChainEditor.editName().getParentContext());
    }

    @Test
    void testPrint() {
        for (int i = 0; i < 16; i++) {
            ropChainEditor.getCollection().setName("testName" + i);
            ropChainEditor.add().handleInput("").handleInput(String.valueOf(i)).handleInput("");
            assertEquals(ropChainEditor.getCollection().getScript() + "\n", ropChainEditor.print().getContextString());
        }

        assertEquals(TextViewer.class, ropChainEditor.print().getClass());
        assertEquals(ropChainEditor, ropChainEditor.print().getParentContext());
        assertEquals(ropChainEditor, ropChainEditor.print().handleInput(""));
    }

    @Test
    void testSave() {
        assertEquals(SaveFile.class, ropChainEditor.save().getClass());
        assertEquals(ropChainEditor, ropChainEditor.save().getParentContext());
    }

    @Test
    void testLoad() {
        assertEquals(LoadFile.class, ropChainEditor.load().getClass());
        assertEquals(ropChainEditor, ropChainEditor.load().getParentContext());
    }
}
