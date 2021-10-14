package ui;

import model.RopChain;

public class NewRopChainContext extends PromptContext {
    private RopChain ropChain;

    // EFFECTS: creates a NewRopChainContext with a new Payload,
    //          null parentContext, and a prompt and defaultResponse
    public NewRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    public NewRopChainContext(ConsoleContext parentContext, RopChain ropChain) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength()));

        this.ropChain = ropChain;
    }

    // EFFECTS: returns a RopChainEditor with a RopChain with the given name or default name
    ConsoleContext handleInput(String input) {
        if (ropChain == null) {
            ropChain = new RopChain();
            ropChain.setName(handleInputInternal(input));

            return new NewRopChainContext(getParentContext(), ropChain);
        } else {
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
}
