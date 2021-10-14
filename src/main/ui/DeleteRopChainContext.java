package ui;

public class DeleteRopChainContext extends PromptContext {

    public DeleteRopChainContext(ConsoleContext parentContext) {
        super(parentContext, "Index", String.valueOf(((PayloadEditor) parentContext).getCollection().getLength() - 1));
    }

    ConsoleContext handleInputInternal(String input) {
        int index = Integer.parseInt(input);

        if (index >= 0) {
            ((PayloadEditor) getParentContext()).getCollection().remove(index);
        }

        return getParentContext();
    }
}
