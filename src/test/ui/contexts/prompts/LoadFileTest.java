package ui.contexts.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoadFileTest {
    private LoadFile loadFilePayload;
    private LoadFile loadFileRopChain;
    private PayloadEditor payloadEditor;
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().setName("testNamePayload");

        ropChainEditor = new RopChainEditor(new RopChain(), payloadEditor);
        ropChainEditor.getCollection().setName("testNameRopChain");

        loadFilePayload = new LoadFile(payloadEditor);
        loadFileRopChain = new LoadFile(ropChainEditor);
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, loadFilePayload.getParentContext());
        assertEquals(ropChainEditor, loadFileRopChain.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("File name (default ./data/testNamePayload.json): ", loadFilePayload.getContextString());
        assertEquals("File name (default ./data/testNameRopChain.json): ", loadFileRopChain.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, loadFilePayload.handleInput("FileDoesNotExist"));
        assertNotEquals(payloadEditor, loadFilePayload.handleInput("./data/payload.json"));

        assertEquals(ropChainEditor, loadFileRopChain.handleInput("FileDoesNotExist"));
        assertNotEquals(ropChainEditor, loadFileRopChain.handleInput("./data/ropChain.json"));
    }
}
