package ui;

import model.RopChain;

public class NewRopChainContext extends PromptContext {
    private RopChain ropChain;

    public NewRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "New ROPchain name", "ropChain");
    }

    public NewRopChainContext(ConsoleContext parentContext, RopChain ropChain) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength()));

        this.ropChain = ropChain;
    }

    ConsoleContext handleInput(String input) {
        input = handleInputInternal(input);

        if (ropChain == null) {
            return readName(input);
        } else {
            return readIndex(input);
        }
    }

    ConsoleContext readName(String input) {
        ropChain = new RopChain();
        ropChain.setName(input);

        return new NewRopChainContext(getParentContext(), ropChain);
    }

    ConsoleContext readIndex(String input) {
        int index = Integer.parseInt(input);

        if (index >= 0) {
            ((CollectionEditor) getParentContext()).getCollection().add(
                    ropChain,
                    index
            );
        }

        return getParentContext();
    }
}