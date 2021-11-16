package ui.cli.prompts;

import model.RopChain;
import ui.cli.ConsoleContext;
import ui.cli.menus.CollectionEditor;
import ui.cli.menus.PayloadEditor;
import ui.cli.menus.RopChainEditor;

// Represents a UI context that selects a RopChain from a Payload
public class RopChainSelector extends PromptContext {

    // EFFECTS: Creates a new RopChainSelector with the given parentContext.
    public RopChainSelector(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    // EFFECTS: Returns a RopChainEditor context with this parentContext and the ropChain at the given index.
    //          If no such ropChain exists, returns the parentContext instead.
    @Override
    public ConsoleContext handleInputInternal(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return getParentContext();
            }
        }

        int index = Integer.parseInt(input);

        RopChain ropChain = (RopChain) ((CollectionEditor) getParentContext()).getCollection().get(index);

        if (ropChain != null) {
            return new RopChainEditor(
                    getParentContext(), ropChain
            );
        }

        return getParentContext();
    }
}
