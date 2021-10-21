package ui.contexts.prompts;

import model.Payload;
import model.RopChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RopChainSelectorTest {
    private RopChainSelector ropChainSelector;
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().add(new RopChain(), 0);
        ropChainSelector = new RopChainSelector(payloadEditor);
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, ropChainSelector.getParentContext());
    }

    @Test
    void testGetContextString() {
        for (int i = 0; i < 16; i++) {
            assertEquals("Index (default " + i + "): ", ropChainSelector.getContextString());
            payloadEditor.getCollection().add(new RopChain(), 0);
            ropChainSelector = new RopChainSelector(payloadEditor);
        }
    }

    @Test
    void testHandleInputInvalid() {
        assertEquals(payloadEditor, ropChainSelector.handleInput("-1"));
        assertEquals(payloadEditor, ropChainSelector.handleInput("1"));
    }

    @Test
    void testHandleInputValid() {
        payloadEditor = new PayloadEditor(new Payload());
        ArrayList<RopChain> ropChainList = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            ropChainList.add(new RopChain());
            payloadEditor.getCollection().add(ropChainList.get(i), i);
        }

        ropChainSelector = new RopChainSelector(payloadEditor);

        assertEquals(RopChainEditor.class, ropChainSelector.handleInput("").getClass());
        assertEquals(payloadEditor, ropChainSelector.handleInput("").getParentContext());

        for (int i = 0; i < 16; i++) {
            assertEquals(
                    ropChainList.get(i),
                    ((RopChainEditor) ropChainSelector.handleInput(String.valueOf(i))).getCollection()
            );
        }
    }
}
