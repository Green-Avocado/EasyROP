package ui;

import model.RopChain;

public class OpenRopChainContext extends PromptContext {

    public OpenRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    ConsoleContext handleInput(String input) {
        int index = Integer.parseInt(handleInputInternal(input));

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
