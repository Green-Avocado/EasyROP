package ui.contexts.prompts;

import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

public class OpenRopChainContext extends PromptContext {

    public OpenRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    ConsoleContext handleInputInternal(String input) {
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
