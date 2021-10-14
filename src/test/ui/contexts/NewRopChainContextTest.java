package ui.contexts;

import model.Payload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewRopChainContextTest {
    private ConsoleContext parentContext;
    private NewRopChainContext nameContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        nameContext = new NewRopChainContext(parentContext);
    }

    @Test
    void testConstructor() {
        assertEquals(parentContext, nameContext.getParentContext());
    }

    @Test
    void testHandleInputDefaultName() {
        assertEquals(
                "ropChain",
                ((PayloadEditor) nameContext.handleInput("").handleInput("0")).getCollection().get(0).getName()
        );
    }

    @Test
    void testHandleInputProvidedName() {
        assertEquals(
                "ropChain0",
                ((PayloadEditor) nameContext.handleInput("ropChain0").handleInput("0")).getCollection().get(0).getName()
        );

        nameContext = new NewRopChainContext(parentContext);
        assertEquals(
                "ROPchain1",
                ((PayloadEditor) nameContext.handleInput("ROPchain1").handleInput("0")).getCollection().get(0).getName()
        );
    }

    @Test
    void testGetContextString() {
        assertEquals("New ROPchain name (default ropChain): ", nameContext.getContextString());
    }
}
