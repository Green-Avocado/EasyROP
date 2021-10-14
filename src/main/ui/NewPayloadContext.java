package ui;

import model.Payload;

public class NewPayloadContext extends PromptContext {
    // EFFECTS: creates a new PromptContext which creates a PayloadContext and a Payload with a given name
    public NewPayloadContext() {
        super(new Payload(), "New payload name", "payload");
    }

    // EFFECTS: returns a PayloadEditor with a Payload with the given name or default name
    ConsoleContext handleInput(String input) {
        getCollection().setName(handleInputInternal(input));
        return new PayloadEditor((Payload) getCollection());
    }
}