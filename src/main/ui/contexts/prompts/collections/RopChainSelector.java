package ui.contexts.prompts.collections;

import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;
import ui.contexts.prompts.PromptContext;

// Represents a UI context that selects a RopChain from a Payload
public class RopChainSelector extends PromptContext {

    public RopChainSelector(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    public ConsoleContext handleInputInternal(String input) {
        int index = Integer.parseInt(input);

        RopChain ropChain = (RopChain) ((CollectionEditor) getParentContext()).getCollection().get(index);

        if (index >= 0 && ropChain != null) {
            return new RopChainEditor(
                    ropChain,
                    getParentContext()
            );
        } else {
            return getParentContext();
        }
    }
}
