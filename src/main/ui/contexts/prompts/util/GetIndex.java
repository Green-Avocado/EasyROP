package ui.contexts.prompts.util;

import model.gadgets.ExploitObject;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.prompts.PromptContext;

// Represents a UI context which gets the index of a new ExploitObject to be added to a collection
public class GetIndex extends PromptContext {
    private final ExploitObject exploitObject;

    // REQUIRES: exploitObject is a RopChain object for PayloadEditor parentContexts,
    //           or exploitObject is a Gadget or Padding object
    // EFFECTS: Creates a new GetIndex context with a given parentContext and an exploitObject to insert at the index
    public GetIndex(ConsoleContext parentContext, ExploitObject exploitObject) {
        super(parentContext, "Index", String.valueOf(((CollectionEditor) parentContext).getCollection().getLength()));

        this.exploitObject = exploitObject;
    }

    // REQUIRES: input can be parsed as an integer
    // EFFECTS: If the index is valid, inserts the exploitObject into the collection of the parentContext at the index
    public ConsoleContext handleInputInternal(String input) {
        int index = Integer.parseInt(input);

        if (index >= 0) {
            ((CollectionEditor) getParentContext()).getCollection().add(
                    exploitObject,
                    index
            );
        }

        return getParentContext();
    }
}
