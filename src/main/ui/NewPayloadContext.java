package ui;

import model.Payload;

public class NewPayloadContext extends PromptContext {

    public NewPayloadContext() {
        super(new Payload(), null, "New payload name", "payload");
    }

    // EFFECTS: returns a PayloadEditor with a Payload with the given name or default name
    ConsoleContext handleInput(String input) {
        getCollection().setName(handleInputInternal(input));
        return new PayloadEditor((Payload) getCollection());
    }
}
