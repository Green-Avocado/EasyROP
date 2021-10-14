package ui;

import model.RopChain;

public class NewRopChainContext extends PromptContext {

    // EFFECTS: creates a NewRopChainContext with a new Payload, null parentContext, and a prompt and defaultResponse
    public NewRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    // EFFECTS: returns a RopChainEditor with a RopChain with the given name or default name
    ConsoleContext handleInput(String input) {
        RopChain ropChain = new RopChain();

        ropChain.setName(handleInputInternal(input));
        ((CollectionEditor)getParentContext()).getCollection().add(ropChain, 0);

        return new RopChainEditor(ropChain, getParentContext());
    }
}
