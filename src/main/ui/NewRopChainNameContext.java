package ui;

import model.RopChain;

public class NewRopChainNameContext extends PromptContext {

    // EFFECTS: creates a NewRopChainNameContext with a new Payload,
    //          null parentContext, and a prompt and defaultResponse
    public NewRopChainNameContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    // EFFECTS: returns a RopChainEditor with a RopChain with the given name or default name
    ConsoleContext handleInput(String input) {
        RopChain ropChain = new RopChain();
        ropChain.setName(handleInputInternal(input));

        return new NewRopChainIndexContext(ropChain, getParentContext());
    }
}
