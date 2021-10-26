package ui.contexts.prompts;

import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;

// Represents a UI context that renames a GadgetCollection of the parent context
public class SetCollectionName extends PromptContext {

    // EFFECTS: Creates a new SetCollectionName context with the given parentContext.
    public SetCollectionName(ConsoleContext parentContext) {
        super(parentContext, "New name", ((CollectionEditor) parentContext).getCollection().getName());
    }

    // MODIFIES: ((CollectionEditor) getParentContext()).getCollection()
    // EFFECTS: Sets the name of the collection of the parentContext to the input string.
    @Override
    public ConsoleContext handleInputInternal(String input) {
        ((CollectionEditor) getParentContext()).getCollection().setName(input);
        return getParentContext();
    }
}
