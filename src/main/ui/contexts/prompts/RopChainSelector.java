package ui.contexts.prompts;

import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

// Represents a UI context that selects a RopChain from a Payload
public class RopChainSelector extends PromptContext {

    // EFFECTS: Creates a new RopChainSelector with the given parentContext.
    public RopChainSelector(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    // REQUIRES: input can be parsed as an integer.
    // EFFECTS: Returns a RopChainEditor context with this parentContext and the ropChain at the given index.
    //          If no such ropChain exists, returns the parentContext instead.
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