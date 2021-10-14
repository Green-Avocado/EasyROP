package ui.contexts;

import model.RopChain;

public class NewRopChainContext extends PromptContext {

    public NewRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    ConsoleContext handleInputInternal(String input) {
        RopChain ropChain = new RopChain();
        ropChain.setName(input);
        return new NewIndexContext(getParentContext(), ropChain);
    }
}
