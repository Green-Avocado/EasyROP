package ui.contexts.prompts.collections;

import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.GetIndex;

// Represents a UI context which creates a new RopChain with a given name and index in a Payload
public class RopChainCreator extends PromptContext {

    public RopChainCreator(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    public ConsoleContext handleInputInternal(String input) {
        RopChain ropChain = new RopChain();
        ropChain.setName(input);
        return new GetIndex(getParentContext(), ropChain);
    }
}
