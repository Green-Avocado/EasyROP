package ui.contexts;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScriptViewerTest {
    private ScriptViewer scriptViewer;
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        scriptViewer = new ScriptViewer(payloadEditor, "testScript");
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, scriptViewer.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("testScript\n", scriptViewer.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, scriptViewer.handleInput(""));
        assertEquals(payloadEditor, scriptViewer.handleInput("aaa"));
    }
}
