package ui;

import model.Payload;
import model.RopChain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewRopChainContextTest {
    private ConsoleContext parentContext;
    private NewRopChainContext nameContext;
    private NewRopChainContext indexContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        nameContext = new NewRopChainContext(parentContext);
        indexContext = new NewRopChainContext(parentContext, new RopChain());
    }

    @Test
    void testConstructor() {
        assertEquals(parentContext, nameContext.getParentContext());
        assertEquals(parentContext, indexContext.getParentContext());
    }

    @Test
    void testHandleInputIndexDefault() {
        Payload payload = new Payload();
        RopChain ropChain;

        for (int i = 0; i < 3; i++) {
            ropChain = new RopChain();
            indexContext = new NewRopChainContext(parentContext, ropChain);
            payload.add(ropChain, payload.getLength());

            assertEquals(
                    payload.getList(),
                    ((PayloadEditor) indexContext.handleInput("")).getCollection().getList()
            );
        }
    }

    @Test
    void testHandleInputIndexProvided() {
        Payload payload = new Payload();
        RopChain ropChain;

        // create non-empty initial Payloads
        for (int i = 0; i < 3; i++) {
            ropChain = new RopChain();
            payload.add(ropChain, 0);
            ((PayloadEditor) parentContext).getCollection().add(ropChain, 0);
        }

        for (int i = 0; i < 3; i += 2) {
            ropChain = new RopChain();
            indexContext = new NewRopChainContext(parentContext, ropChain);
            payload.add(ropChain, i);

            assertEquals(
                    payload.getList(),
                    ((PayloadEditor) indexContext.handleInput(String.valueOf(i))).getCollection().getList()
            );
        }
    }

    @Test
    void testHandleInputIndexOutOfBounds() {
        assertEquals(
                0,
                ((PayloadEditor) indexContext.handleInput("1")).getCollection().getLength()
        );
    }

    @Test
    void testHandleInputIndexNotNonNegative() {
        assertEquals(
                0,
                ((PayloadEditor) indexContext.handleInput("-1")).getCollection().getLength()
        );
    }

    @Test
    void testHandleInputIndexNotAnInteger() {
        // TODO
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
        assertEquals("Index (default 0): ", indexContext.getContextString());
    }
}
