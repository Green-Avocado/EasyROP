package ui;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewRopChainContextTest {
    private NewRopChainContext newRopChainContext;
    private ConsoleContext parentContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        newRopChainContext = new NewRopChainContext(parentContext);
    }

    @Test
    void testConstructor() {
        assertEquals(parentContext, newRopChainContext.getParentContext());
    }

    @Test
    void testHandleInput() {
        // default name
        assertEquals(
                "ropChain",
                ((RopChainEditor)newRopChainContext.handleInput("")).getCollection().getName()
        );

        // provided name
        assertEquals(
                "ropChain0",
                ((RopChainEditor)newRopChainContext.handleInput("ropChain0")).getCollection().getName()
        );
        assertEquals(
                "ROPchain1",
                ((RopChainEditor)newRopChainContext.handleInput("ROPchain1")).getCollection().getName()
        );
    }

    @Test
    void testGetContextString() {
        assertEquals("New ROPchain name (default ropChain): ", newRopChainContext.getContextString());
    }
}
