package ui.contexts.prompts.collections;

import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.NewIndexContext;

public class NewRopChainContext extends PromptContext {

    public NewRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    public ConsoleContext handleInputInternal(String input) {
        RopChain ropChain = new RopChain();
        ropChain.setName(input);
        return new NewIndexContext(getParentContext(), ropChain);
    }
}
