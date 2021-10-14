package ui;

import model.Payload;

public class NewPayloadContext extends PromptContext {

    // EFFECTS: creates a NewPayloadContext with a new Payload, null parentContext, and a prompt and defaultResponse
    public NewPayloadContext() {
        super(null, "New payload name", "payload");
    }

    // EFFECTS: returns a PayloadEditor with a Payload with the given name or default name
    ConsoleContext handleInputInternal(String input) {
        Payload payload = new Payload();

        payload.setName(input);

        return new PayloadEditor(payload);
    }
}
