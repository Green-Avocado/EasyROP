package ui.contexts.prompts.collections;

import model.Payload;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.prompts.PromptContext;

public class PayloadCreator extends PromptContext {

    // EFFECTS: creates a PayloadCreator with a new Payload, null parentContext, and a prompt and defaultResponse
    public PayloadCreator() {
        super(null, "New payload name", "payload");
    }

    // EFFECTS: returns a PayloadEditor with a Payload with the given name or default name
    public ConsoleContext handleInputInternal(String input) {
        Payload payload = new Payload();

        payload.setName(input);

        return new PayloadEditor(payload);
    }
}
