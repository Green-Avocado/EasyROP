package ui;

public class SetCollectionNameContext extends PromptContext {

    public SetCollectionNameContext(ConsoleContext parentContext) {
        super(parentContext, "New name", ((CollectionEditor) parentContext).getCollection().getName());
    }

    ConsoleContext handleInput(String input) {
        ((CollectionEditor) getParentContext()).getCollection().setName(handleInputInternal(input));
        return getParentContext();
    }
}
