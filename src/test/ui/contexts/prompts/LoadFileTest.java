package ui.contexts.prompts;

import model.GadgetCollection;
import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoadFileTest {
    private static final String DOES_NOT_EXIST = "File Does Not Exist";
    private static final String PAYLOAD_FILE = "./data/testReadPayload.json";
    private static final String ROPCHAIN_FILE = "./data/testReadRopChain.json";

    private LoadFile loadFilePayload;
    private LoadFile loadFileRopChain;
    private PayloadEditor payloadEditor;
    private RopChainEditor ropChainEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().setName("testReadPayload");

        ropChainEditor = new RopChainEditor(payloadEditor, new RopChain());
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
        assertEquals("File name (default " + PAYLOAD_FILE + "): ", loadFilePayload.getContextString());
        assertEquals("File name (default " + ROPCHAIN_FILE + "): ", loadFileRopChain.getContextString());
    }

    @Test
    void testHandleInputFail() {
        GadgetCollection oldCollection;

        assertEquals(payloadEditor, loadFilePayload.handleInput(DOES_NOT_EXIST));
        assertEquals(payloadEditor, loadFilePayload.handleInput(ROPCHAIN_FILE));
        assertEquals(ropChainEditor, loadFileRopChain.handleInput(DOES_NOT_EXIST));
        assertEquals(ropChainEditor, loadFileRopChain.handleInput(PAYLOAD_FILE));

        oldCollection = payloadEditor.getCollection();
        assertEquals(oldCollection, ((CollectionEditor) loadFilePayload.handleInput(DOES_NOT_EXIST)).getCollection());
        assertEquals(oldCollection, ((CollectionEditor) loadFilePayload.handleInput(ROPCHAIN_FILE)).getCollection());

        oldCollection = ropChainEditor.getCollection();
        assertEquals(oldCollection, ((CollectionEditor) loadFileRopChain.handleInput(DOES_NOT_EXIST)).getCollection());
        assertEquals(oldCollection, ((CollectionEditor) loadFileRopChain.handleInput(PAYLOAD_FILE)).getCollection());
    }

    @Test
    void testHandleInputSucceedPayload() {
        GadgetCollection oldCollection;

        assertEquals(payloadEditor, loadFilePayload.handleInput(PAYLOAD_FILE));
        assertEquals(payloadEditor, loadFilePayload.handleInput(""));

        oldCollection = payloadEditor.getCollection();
        assertNotEquals(oldCollection, ((CollectionEditor) loadFilePayload.handleInput("")).getCollection());
        oldCollection = payloadEditor.getCollection();
        assertNotEquals(oldCollection, ((CollectionEditor) loadFilePayload.handleInput(PAYLOAD_FILE)).getCollection());

        assertEquals(payloadEditor.getParentContext(), loadFilePayload.handleInput("").getParentContext());
    }

    @Test
    void testHandleInputSucceedRopChain() {
        GadgetCollection oldCollection;

        assertEquals(ropChainEditor, loadFileRopChain.handleInput(ROPCHAIN_FILE));
        assertEquals(ropChainEditor, loadFileRopChain.handleInput(""));

        oldCollection = ropChainEditor.getCollection();
        assertNotEquals(oldCollection, ((CollectionEditor) loadFileRopChain.handleInput("")).getCollection());
        oldCollection = ropChainEditor.getCollection();
        assertNotEquals(oldCollection, ((CollectionEditor) loadFileRopChain.handleInput(ROPCHAIN_FILE)).getCollection());

        assertEquals(ropChainEditor.getParentContext(), loadFileRopChain.handleInput("").getParentContext());
    }
}
