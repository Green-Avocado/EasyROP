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

        // empty input
        oldCollection = payloadEditor.getCollection();
        assertEquals(payloadEditor, loadFilePayload.handleInput(""));
        assertEquals("testReadPayload", payloadEditor.getCollection().getName());
        assertEquals(1, payloadEditor.getCollection().getLength());
        assertEquals(oldCollection, payloadEditor.getCollection());

        // reset
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().setName("payload");
        loadFilePayload = new LoadFile(payloadEditor);

        // filename input
        oldCollection = payloadEditor.getCollection();
        assertEquals(payloadEditor, loadFilePayload.handleInput(PAYLOAD_FILE));
        assertEquals("testReadPayload", payloadEditor.getCollection().getName());
        assertEquals(1, payloadEditor.getCollection().getLength());
        assertEquals(oldCollection, payloadEditor.getCollection());

        // preserve parent
        assertEquals(payloadEditor.getParentContext(), loadFilePayload.handleInput("").getParentContext());
        assertEquals(payloadEditor.getParentContext(), loadFilePayload.handleInput(PAYLOAD_FILE).getParentContext());
    }

    @Test
    void testHandleInputSucceedRopChain() {
        GadgetCollection oldCollection;

        // empty input
        oldCollection = ropChainEditor.getCollection();
        assertEquals(ropChainEditor, loadFileRopChain.handleInput(""));
        assertEquals("testReadRopChain", ropChainEditor.getCollection().getName());
        assertEquals(10, ropChainEditor.getCollection().getLength());
        assertEquals(oldCollection, ropChainEditor.getCollection());

        // reset
        ropChainEditor = new RopChainEditor(payloadEditor, new RopChain());
        ropChainEditor.getCollection().setName("ropChain");
        loadFileRopChain = new LoadFile(ropChainEditor);

        // filename input
        oldCollection = ropChainEditor.getCollection();
        assertEquals(ropChainEditor, loadFileRopChain.handleInput(ROPCHAIN_FILE));
        assertEquals("testReadRopChain", ropChainEditor.getCollection().getName());
        assertEquals(10, ropChainEditor.getCollection().getLength());
        assertEquals(oldCollection, ropChainEditor.getCollection());

        // preserve parent
        assertEquals(ropChainEditor.getParentContext(), loadFileRopChain.handleInput("").getParentContext());
        assertEquals(ropChainEditor.getParentContext(), loadFileRopChain.handleInput(ROPCHAIN_FILE).getParentContext());
    }
}
