package ui.contexts;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.prompts.collections.RopChainCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RopChainCreatorTest {
    private ConsoleContext parentContext;
    private RopChainCreator nameContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        nameContext = new RopChainCreator(parentContext);
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

        nameContext = new RopChainCreator(parentContext);
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
