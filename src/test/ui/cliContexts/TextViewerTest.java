package ui.cliContexts;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cliContexts.menus.PayloadEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextViewerTest {
    private TextViewer textViewer;
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        textViewer = new TextViewer(payloadEditor, "testScript");
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, textViewer.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("testScript\n", textViewer.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, textViewer.handleInput(""));
        assertEquals(payloadEditor, textViewer.handleInput("aaa"));
    }
}
