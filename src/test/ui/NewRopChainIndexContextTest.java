package ui;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewRopChainIndexContextTest {
    private NewRopChainIndexContext indexContext;
    private ConsoleContext parentContext;

    @BeforeEach
    void runBefore() {
        parentContext = new PayloadEditor(new Payload());
        indexContext = new NewRopChainIndexContext(new RopChain(), parentContext);
    }

    @Test
    void testConstructor() {
        assertEquals(parentContext, indexContext.getParentContext());
    }

    @Test
    void testHandleInputDefaultIndex() {
        Payload payload = new Payload();
        RopChain ropChain;

        for (int i = 0; i < 3; i++) {
            ropChain = new RopChain();
            indexContext = new NewRopChainIndexContext(ropChain, parentContext);
            payload.add(ropChain, payload.getLength());

            assertEquals(
                    payload.getList(),
                    ((PayloadEditor) indexContext.handleInput("").getParentContext()).getCollection().getList()
            );
        }
    }

    @Test
    void testHandleInputProvidedIndex() {
        Payload payload = new Payload();
        RopChain ropChain;

        // create non-empty initial Payloads
        for (int i = 0; i < 3; i++) {
            ropChain = new RopChain();
            payload.add(ropChain, 0);
            ((PayloadEditor)parentContext).getCollection().add(ropChain, 0);
        }

        for (int i = 0; i < 3; i += 2) {
            ropChain = new RopChain();
            indexContext = new NewRopChainIndexContext(ropChain, parentContext);
            payload.add(ropChain, i);

            assertEquals(
                    payload.getList(),
                    ((PayloadEditor) indexContext.handleInput(String.valueOf(i)).getParentContext())
                            .getCollection().getList()
            );
        }
    }

    @Test
    void testHandleInputOutOfBounds() {
        assertEquals(
                0,
                ((PayloadEditor) indexContext.handleInput("1").getParentContext())
                        .getCollection().getLength()
        );
    }

    @Test
    void testHandleInputNotNonNegative() {
        assertEquals(
                0,
                ((PayloadEditor) indexContext.handleInput("-1").getParentContext())
                        .getCollection().getLength()
        );
    }

    @Test
    void testHandleInputNotAnInteger() {
        // TODO
    }

    @Test
    void testGetContextString() {
        assertEquals("Index (default 0): ", indexContext.getContextString());
    }
}
