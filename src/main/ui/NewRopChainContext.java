package ui;

import model.RopChain;

public class NewRopChainContext extends PromptContext {

    // EFFECTS: creates a NewRopChainContext with a new Payload, null parentContext, and a prompt and defaultResponse
    public NewRopChainContext(ConsoleContext parentContext) {
        super(new RopChain(), parentContext, "New ROPchain name", "ropChain");
    }

    // EFFECTS: returns a RopChainEditor with a RopChain with the given name or default name
    ConsoleContext handleInput(String input) {
        getCollection().setName(handleInputInternal(input));
        return new RopChainEditor((RopChain) getCollection(), getParentContext());
    }
}
