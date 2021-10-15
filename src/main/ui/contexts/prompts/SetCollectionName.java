package ui.contexts.prompts;

import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;

// Represents a UI context that renames a GadgetCollection
public class SetCollectionName extends PromptContext {

    public SetCollectionName(ConsoleContext parentContext) {
        super(parentContext, "New name", ((CollectionEditor) parentContext).getCollection().getName());
    }

    public ConsoleContext handleInputInternal(String input) {
        ((CollectionEditor) getParentContext()).getCollection().setName(input);
        return getParentContext();
    }
}
