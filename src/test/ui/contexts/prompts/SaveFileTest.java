package ui.contexts.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveFileTest {
    private SaveFile saveFilePayload;
    private SaveFile saveFileRopChain;
    private PayloadEditor payloadEditor;
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().setName("testNamePayload");

        ropChainEditor = new RopChainEditor(new RopChain(), payloadEditor);
        ropChainEditor.getCollection().setName("testNameRopChain");

        saveFilePayload = new SaveFile(payloadEditor);
        saveFileRopChain = new SaveFile(ropChainEditor);
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, saveFilePayload.getParentContext());
        assertEquals(ropChainEditor, saveFileRopChain.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("File name (default ./data/testNamePayload.json): ", saveFilePayload.getContextString());
        assertEquals("File name (default ./data/testNameRopChain.json): ", saveFileRopChain.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, saveFilePayload.handleInput("/etc/passwd"));
        assertEquals(payloadEditor, saveFilePayload.handleInput("./data/payload.json"));

        assertEquals(ropChainEditor, saveFileRopChain.handleInput("/etc/passwd"));
        assertEquals(ropChainEditor, saveFileRopChain.handleInput("./data/ropChain.json"));
    }
}
