package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewPayloadContextTest {
    private NewPayloadContext newPayloadContext;

    @BeforeEach
    void runBefore() {
        newPayloadContext = new NewPayloadContext();
    }

    @Test
    void testConstructor() {
        assertEquals(0, newPayloadContext.getCollection().getLength());
    }

    @Test
    void testHandleInput() {
        // default name
        assertEquals("payload", newPayloadContext.handleInput("").getCollection().getName());

        // provided name
        assertEquals("payload0", newPayloadContext.handleInput("payload0").getCollection().getName());
        assertEquals("Payload1", newPayloadContext.handleInput("Payload1").getCollection().getName());
    }

    @Test
    void testGetContextString() {
        assertEquals("New payload name (default payload): ", newPayloadContext.getContextString());
    }
}
