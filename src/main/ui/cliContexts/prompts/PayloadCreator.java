package ui.cliContexts.prompts;

import model.Payload;
import ui.cliContexts.ConsoleContext;
import ui.cliContexts.menus.PayloadEditor;

// Represents a UI context which creates a new Payload object with a specified name
public class PayloadCreator extends PromptContext {

    // EFFECTS: creates a PayloadCreator with a new Payload, null parentContext, and a prompt and defaultResponse
    public PayloadCreator() {
        super(null, "New payload name", "payload");
    }

    // EFFECTS: returns a PayloadEditor with a Payload with the given name or default name
    @Override
    public ConsoleContext handleInputInternal(String input) {
        Payload payload = new Payload();

        payload.setName(input);

        return new PayloadEditor(payload);
    }
}
