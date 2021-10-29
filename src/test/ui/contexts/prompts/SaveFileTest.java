package ui.contexts.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.TextViewer;
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
        payloadEditor.getCollection().setName("testSavePayload");

        ropChainEditor = new RopChainEditor(payloadEditor, new RopChain());
        ropChainEditor.getCollection().setName("testSaveRopChain");

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
        assertEquals("File name (default ./data/testSavePayload.json): ", saveFilePayload.getContextString());
        assertEquals("File name (default ./data/testSaveRopChain.json): ", saveFileRopChain.getContextString());
    }

    @Test
    void testHandleInput() {
        // payload fail
        assertEquals(payloadEditor, saveFilePayload.handleInput("./data").handleInput(""));
        assertEquals(TextViewer.class, saveFilePayload.handleInput("./data").getClass());
        assertEquals(
                "Error: could not save to file ./data (Is a directory)\n",
                saveFilePayload.handleInput("./data").getContextString()
        );

        // ropChain fail
        assertEquals(ropChainEditor, saveFileRopChain.handleInput("./data").handleInput(""));
        assertEquals(TextViewer.class, saveFileRopChain.handleInput("./data").getClass());
        assertEquals(
                "Error: could not save to file ./data (Is a directory)\n",
                saveFileRopChain.handleInput("./data").getContextString()
        );

        // success
        assertEquals(payloadEditor, saveFilePayload.handleInput("./data/testSavePayload.json"));
        assertEquals(ropChainEditor, saveFileRopChain.handleInput("./data/testSaveRopChain.json"));
    }
}
