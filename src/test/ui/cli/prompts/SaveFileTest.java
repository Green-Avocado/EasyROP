package ui.cli.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.TextViewer;
import ui.cli.menus.PayloadEditor;
import ui.cli.menus.RopChainEditor;

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
        assertEquals("File name (default testSavePayload.json): ", saveFilePayload.getContextString());
        assertEquals("File name (default testSaveRopChain.json): ", saveFileRopChain.getContextString());
    }

    @Test
    void testHandleInput() {
        // payload fail
        assertEquals(payloadEditor, saveFilePayload.handleInput("../").handleInput(""));
        assertEquals(TextViewer.class, saveFilePayload.handleInput("../").getClass());
        assertEquals(
                "Error: could not save to file ./data/payloads/.. (Is a directory)\n",
                saveFilePayload.handleInput("../").getContextString()
        );

        // ropChain fail
        assertEquals(ropChainEditor, saveFileRopChain.handleInput("../").handleInput(""));
        assertEquals(TextViewer.class, saveFileRopChain.handleInput("../").getClass());
        assertEquals(
                "Error: could not save to file ./data/ropchains/.. (Is a directory)\n",
                saveFileRopChain.handleInput("../").getContextString()
        );

        // success
        assertEquals(payloadEditor, saveFilePayload.handleInput("testSavePayload.json"));
        assertEquals(ropChainEditor, saveFileRopChain.handleInput("testSaveRopChain.json"));
    }
}
