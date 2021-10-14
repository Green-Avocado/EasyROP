package ui;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewRopChainNameContextTest {
    private NewRopChainNameContext nameContext;
    private ConsoleContext parentContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        nameContext = new NewRopChainNameContext(parentContext);
    }

    @Test
    void testConstructor() {
        assertEquals(parentContext, nameContext.getParentContext());
    }

    @Test
    void testHandleInput() {
        // default name
        assertEquals(
                "ropChain",
                ((RopChainEditor) nameContext.handleInput("").handleInput("")).getCollection().getName()
        );

        // provided name
        assertEquals(
                "ropChain0",
                ((RopChainEditor) nameContext.handleInput("ropChain0").handleInput("")).getCollection().getName()
        );
        assertEquals(
                "ROPchain1",
                ((RopChainEditor) nameContext.handleInput("ROPchain1").handleInput("")).getCollection().getName()
        );
    }

    @Test
    void testGetContextString() {
        assertEquals("New ROPchain name (default ropChain): ", nameContext.getContextString());
    }
}
