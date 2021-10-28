package ui.contexts.menus;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.*;
import ui.contexts.ScriptViewer;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PayloadEditorTest {
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
    }

    @Test
    void testConstructor() {
        assertNull(payloadEditor.getParentContext());

        Payload payload = new Payload();
        assertEquals(payload, new PayloadEditor(payload).getCollection());
    }

    @Test
    void testSetCollection() {
        Payload payload = new Payload();
        payloadEditor.setCollection(payload);
        assertEquals(payload, payloadEditor.getCollection());
    }

    @Test
    void testGetMenu() {
        assertEquals(
                Arrays.asList(
                        "[N]ew ROP chain",
                        "[o]pen ROP chain",
                        "[m]ove ROP chain",
                        "[d]elete ROP chain",
                        "[p]rint payload",
                        "[e]dit name",
                        "[s]ave",
                        "[l]oad",
                        "[q]uit"
                ),
                payloadEditor.getMenu()
        );
    }

    @Test
    void testAdd() {
        // test context string is expected and parentContext is payloadEditor
        ConsoleContext context = payloadEditor.add();
        assertEquals("New ROPchain name (default ropChain): ", context.getContextString());
        assertEquals(payloadEditor, context.getParentContext());

        // test context string is expected
        context = context.handleInput("testName");
        assertEquals("Index (default 0): ", context.getContextString());

        // test that context returns to payloadEditor
        context = context.handleInput("");
        assertEquals(payloadEditor, context);

        // test ropChain name is expected
        RopChain ropChain = (RopChain) payloadEditor.getCollection().get(0);
        assertEquals("testName", ropChain.getName());
    }

    @Test
    void testOpen() {
        // empty collection
        assertEquals(payloadEditor, payloadEditor.open());

        // many items in collection
        for (int i = 0; i < 16; i++) {
            // add new RopChain
            payloadEditor.add().handleInput(String.valueOf(i)).handleInput("");

            // test context string is expected and parentContext is payloadEditor
            ConsoleContext context = payloadEditor.open();
            assertEquals("Index (default " + i + "): ", context.getContextString());
            assertEquals(payloadEditor, context.getParentContext());

            // test ropChain name is expected
            context = context.handleInput("");
            assertEquals(String.valueOf(i), ((RopChainEditor) context).getCollection().getName());
        }
    }

    @Test
    void testGetContextString() {
        // nested for loop tests different sized Payloads
        for (int i = 0; i < 16; i++) {
            payloadEditor = new PayloadEditor(new Payload());
            payloadEditor.getCollection().setName("testName");

            StringBuilder expectedString = new StringBuilder();
            expectedString.append("testName:\n");

            // adds j elements to the Payload
            for (int j = 0; j < i; j++) {
                payloadEditor.add().handleInput("testChain" + j).handleInput("");
                expectedString.append(j).append(": testChain").append(j).append("\n");
            }

            expectedString.append(String.join("  ", payloadEditor.getMenu())).append("\n");

            assertEquals(expectedString.toString(), payloadEditor.getContextString());
        }
    }

    @Test
    void testHandleInput() {
        // case insensitivity
        assertEquals(
                payloadEditor.handleInput("n").getContextString(),
                payloadEditor.handleInput("N").getContextString()
        );

        // empty input
        assertEquals(
                payloadEditor.handleInput("n").getContextString(),
                payloadEditor.handleInput("").getContextString()
        );

        // invalid input
        assertEquals(payloadEditor, payloadEditor.handleInput("invalid input"));
    }

    @Test
    void testHandleInputN() {
        ConsoleContext expectedContext = payloadEditor.add();
        ConsoleContext actualContext = payloadEditor.handleInput("n");

        for (int i = 0; i < 2; i++) {
            assertEquals(expectedContext.getContextString(), actualContext.getContextString());
            assertEquals(expectedContext.getClass(), actualContext.getClass());

            expectedContext = expectedContext.handleInput("");
            actualContext = actualContext.handleInput("");
        }

        assertEquals(expectedContext, actualContext);
    }

    @Test
    void testHandleInputO() {
        for (int i = 0; i < 16; i++) {
            // add new RopChain
            payloadEditor.add().handleInput(String.valueOf(i)).handleInput("");

            // test context string is expected
            assertEquals(payloadEditor.open().getContextString(), payloadEditor.handleInput("o").getContextString());
            assertEquals(
                    payloadEditor.open().handleInput("").getContextString(),
                    payloadEditor.handleInput("o").handleInput("").getContextString()
            );
        }

        assertEquals(payloadEditor.open().getClass(), payloadEditor.handleInput("o").getClass());
    }

    @Test
    void testHandleInputM() {
        payloadEditor.add().handleInput("").handleInput("");

        for (int i = 0; i < 16; i++) {
            // add new RopChain
            payloadEditor.add().handleInput(String.valueOf(i)).handleInput("");

            // test context string is expected
            ConsoleContext expectedContext = payloadEditor.move().handleInput("");
            String expectedContextString = expectedContext.getContextString();
            expectedContext.handleInput("");

            ConsoleContext actualContext = payloadEditor.move().handleInput("");
            String actualContextString = actualContext.getContextString();
            actualContext.handleInput("");

            assertEquals(payloadEditor.move().getContextString(), payloadEditor.handleInput("m").getContextString());
            assertEquals(expectedContextString, actualContextString);
        }

        assertEquals(payloadEditor.move().getClass(), payloadEditor.handleInput("m").getClass());
    }

    @Test
    void testHandleInputD() {
        assertEquals(payloadEditor.delete().getContextString(), payloadEditor.handleInput("d").getContextString());
        assertEquals(payloadEditor.delete().getClass(), payloadEditor.handleInput("d").getClass());
    }

    @Test
    void testHandleInputP() {
        for (int i = 0; i < 16; i++) {
            payloadEditor.getCollection().setName("testName" + i);
            payloadEditor.add().handleInput("testChain" + i).handleInput("");
            assertEquals(payloadEditor.print().getContextString(), payloadEditor.handleInput("p").getContextString());
        }

        assertEquals(payloadEditor.print().getClass(), payloadEditor.handleInput("p").getClass());
    }

    @Test
    void testHandleInputE() {
        assertEquals(payloadEditor.editName().getContextString(), payloadEditor.handleInput("e").getContextString());
        assertEquals(payloadEditor.editName().getClass(), payloadEditor.handleInput("e").getClass());
    }

    @Test
    void testHandleInputS() {
        assertEquals(payloadEditor.save().getContextString(), payloadEditor.handleInput("s").getContextString());
        assertEquals(payloadEditor.save().getClass(), payloadEditor.handleInput("s").getClass());
    }

    @Test
    void testHandleInputL() {
        assertEquals(payloadEditor.load().getContextString(), payloadEditor.handleInput("l").getContextString());
        assertEquals(payloadEditor.load().getClass(), payloadEditor.handleInput("l").getClass());
    }

    @Test
    void testHandleInputQ() {
        assertNull(payloadEditor.handleInput("q"));
    }

    @Test
    void testGetCollection() {
        assertEquals(Payload.class, payloadEditor.getCollection().getClass());
    }

    @Test
    void testDelete() {
        // length <= 0
        assertEquals(payloadEditor, payloadEditor.delete());

        // length > 0
        payloadEditor.add().handleInput("").handleInput("");
        assertEquals(ExploitObjectRemover.class, payloadEditor.delete().getClass());
        assertEquals(payloadEditor, payloadEditor.delete().getParentContext());
    }

    @Test
    void testMove() {
        // length <= 1
        assertEquals(payloadEditor, payloadEditor.move());

        payloadEditor.add().handleInput("").handleInput("");
        assertEquals(payloadEditor, payloadEditor.move());

        // length > 1
        payloadEditor.add().handleInput("").handleInput("");
        assertEquals(ExploitObjectMover.class, payloadEditor.move().getClass());
        assertEquals(payloadEditor, payloadEditor.move().getParentContext());
    }

    @Test
    void testEditName() {
        payloadEditor.editName().handleInput("testName");
        assertEquals("testName", payloadEditor.getCollection().getName());

        assertEquals(SetCollectionName.class, payloadEditor.editName().getClass());
        assertEquals(payloadEditor, payloadEditor.editName().getParentContext());
    }

    @Test
    void testPrint() {
        for (int i = 0; i < 16; i++) {
            payloadEditor.getCollection().setName("testName" + i);
            payloadEditor.add().handleInput("testChain" + i).handleInput("");
            assertEquals(payloadEditor.getCollection().getScript() + "\n", payloadEditor.print().getContextString());
        }

        assertEquals(ScriptViewer.class, payloadEditor.print().getClass());
        assertEquals(payloadEditor, payloadEditor.print().getParentContext());
        assertEquals(payloadEditor, payloadEditor.print().handleInput(""));
    }

    @Test
    void testSave() {
        assertEquals(SaveFile.class, payloadEditor.save().getClass());
        assertEquals(payloadEditor, payloadEditor.save().getParentContext());
    }

    @Test
    void testLoad() {
        assertEquals(LoadFile.class, payloadEditor.load().getClass());
        assertEquals(payloadEditor, payloadEditor.load().getParentContext());
    }
}
