package ui.contexts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.prompts.collections.NewPayloadContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NewPayloadContextTest {
    private NewPayloadContext newPayloadContext;

    @BeforeEach
    void runBefore() {
        newPayloadContext = new NewPayloadContext();
    }

    @Test
    void testConstructor() {
        assertNull(newPayloadContext.getParentContext());
    }

    @Test
    void testHandleInput() {
        // default name
        Assertions.assertEquals(
                "payload",
                ((PayloadEditor) newPayloadContext.handleInput("")).getCollection().getName()
        );

        // provided name
        assertEquals(
                "payload0",
                ((PayloadEditor) newPayloadContext.handleInput("payload0")).getCollection().getName()
        );
        assertEquals(
                "Payload1",
                ((PayloadEditor) newPayloadContext.handleInput("Payload1")).getCollection().getName()
        );
    }

    @Test
    void testGetContextString() {
        assertEquals("New payload name (default payload): ", newPayloadContext.getContextString());
    }
}
