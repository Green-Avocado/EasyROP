package ui.cliContexts.prompts;

import model.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cliContexts.menus.PayloadEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetCollectionNameTest {
    private SetCollectionName setCollectionName;
    private PayloadEditor payloadEditor;

    @BeforeEach
    void runBefore() {
        payloadEditor = new PayloadEditor(new Payload());
        payloadEditor.getCollection().setName("payload");
        setCollectionName = new SetCollectionName(payloadEditor);
    }

    @Test
    void testConstructor() {
        assertEquals(payloadEditor, setCollectionName.getParentContext());
    }

    @Test
    void testGetContextString() {
        assertEquals("New name (default payload): ", setCollectionName.getContextString());

        payloadEditor.getCollection().setName("otherName");
        setCollectionName = new SetCollectionName(payloadEditor);

        assertEquals("New name (default otherName): ", setCollectionName.getContextString());
    }

    @Test
    void testHandleInput() {
        assertEquals(payloadEditor, setCollectionName.handleInput(""));
        assertEquals("payload", payloadEditor.getCollection().getName());

        assertEquals(payloadEditor, setCollectionName.handleInput("otherName"));
        assertEquals("otherName", payloadEditor.getCollection().getName());
    }
}
