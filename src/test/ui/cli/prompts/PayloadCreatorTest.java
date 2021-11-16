package ui.cli.prompts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.menus.PayloadEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PayloadCreatorTest {
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
        assertEquals("payload", ((PayloadEditor) payloadCreator.handleInput("")).getCollection().getName());
        assertEquals("testName", ((PayloadEditor) payloadCreator.handleInput("testName")).getCollection().getName());
    }
}
