package ui.contexts.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructionsGadgetCreatorTest {
    private InstructionsGadgetCreator instructionsGadgetCreator;
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        ropChainEditor = new RopChainEditor(new RopChain(), new PayloadEditor(new Payload()));
        instructionsGadgetCreator = new InstructionsGadgetCreator(ropChainEditor);
    }

    @Test
    void testConstructor() {
        assertEquals(ropChainEditor, instructionsGadgetCreator.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("Base (default exe): ", instructionsGadgetCreator.getContextString());
        assertEquals("Instruction/quit (default quit): ", instructionsGadgetCreator.handleInput("").getContextString());
    }

    @Test
    void testHandleInputBase() {
        assertEquals(instructionsGadgetCreator, instructionsGadgetCreator.handleInput(""));
    }

    @Test
    void testHandleInputInstructionNotQuit() {
        instructionsGadgetCreator.handleInput("");
        assertEquals(instructionsGadgetCreator, instructionsGadgetCreator.handleInput("ret"));
    }

    @Test
    void testHandleInputInstructionQuitEmpty() {
        instructionsGadgetCreator.handleInput("");
        assertEquals(ropChainEditor, instructionsGadgetCreator.handleInput("quit"));
        assertEquals(ropChainEditor, instructionsGadgetCreator.handleInput(""));
    }

    @Test
    void testHandleInputInstructionQuitNotEmpty() {
        instructionsGadgetCreator.handleInput("");
        instructionsGadgetCreator.handleInput("ret");
        assertEquals(AddExploitObjectToIndex.class, instructionsGadgetCreator.handleInput("quit").getClass());
        assertEquals(AddExploitObjectToIndex.class, instructionsGadgetCreator.handleInput("").getClass());
    }
}
