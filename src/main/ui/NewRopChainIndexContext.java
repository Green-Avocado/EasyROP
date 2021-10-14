package ui;

import model.RopChain;

public class NewRopChainIndexContext extends PromptContext {
    private final RopChain ropChain;

    // EFFECTS: creates a NewRopChainIndexContext with a new Payload,
    //          null parentContext, and a prompt and defaultResponse
    public NewRopChainIndexContext(RopChain ropChain, ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor)parentContext).getCollection().getLength()));

        this.ropChain = ropChain;
    }

    // EFFECTS: returns a RopChainEditor with a RopChain with the given name or default name
    ConsoleContext handleInput(String input) {
        int index = Integer.parseInt(handleInputInternal(input));

        if (index >= 0) {
            ((CollectionEditor) getParentContext()).getCollection().add(
                    ropChain,
                    index
            );
        }

        return getParentContext();
    }
}
