package ui.contexts;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayloadEditorTest {
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
    }

    @Test
    void testReset() {
        // TODO
    }

    @Test
    void testGetContext() {
        // TODO
    }

    @Test
    void testGetMenu() {
        // TODO
    }
}
