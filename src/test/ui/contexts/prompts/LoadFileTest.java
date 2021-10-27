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
        payloadEditor.getCollection().setName("testReadPayload");

        ropChainEditor = new RopChainEditor(new RopChain(), payloadEditor);
        ropChainEditor.getCollection().setName("testReadRopChain");

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
        assertEquals("File name (default ./data/testReadPayload.json): ", loadFilePayload.getContextString());
        assertEquals("File name (default ./data/testReadRopChain.json): ", loadFileRopChain.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, loadFilePayload.handleInput("FileDoesNotExist"));
        assertNotEquals(payloadEditor, loadFilePayload.handleInput("./data/testReadPayload.json"));
        assertNotEquals(payloadEditor, loadFilePayload.handleInput(""));

        assertEquals(ropChainEditor, loadFileRopChain.handleInput("FileDoesNotExist"));
        assertNotEquals(ropChainEditor, loadFileRopChain.handleInput("./data/testReadRopChain.json"));
        assertNotEquals(ropChainEditor, loadFileRopChain.handleInput(""));
    }
}
