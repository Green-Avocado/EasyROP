package ui.contexts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.prompts.collections.PayloadCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PayloadCreatorTest {
    private PayloadCreator payloadCreator;

    @BeforeEach
    void runBefore() {
        payloadCreator = new PayloadCreator();
    }

    @Test
    void testConstructor() {
        assertNull(payloadCreator.getParentContext());
    }

    @Test
    void testHandleInput() {
        // default name
        Assertions.assertEquals(
                "payload",
                ((PayloadEditor) payloadCreator.handleInput("")).getCollection().getName()
        );

        // provided name
        assertEquals(
                "payload0",
                ((PayloadEditor) payloadCreator.handleInput("payload0")).getCollection().getName()
        );
        assertEquals(
                "Payload1",
                ((PayloadEditor) payloadCreator.handleInput("Payload1")).getCollection().getName()
        );
    }

    @Test
    void testGetContextString() {
        assertEquals("New payload name (default payload): ", payloadCreator.getContextString());
    }
}
