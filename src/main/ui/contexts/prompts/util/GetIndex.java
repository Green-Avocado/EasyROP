package ui.contexts.prompts.util;

import model.gadgets.ExploitObject;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.prompts.PromptContext;

public class GetIndex extends PromptContext {
    private final ExploitObject exploitObject;

    public GetIndex(ConsoleContext parentContext, ExploitObject exploitObject) {
        super(parentContext, "Index", String.valueOf(((CollectionEditor) parentContext).getCollection().getLength()));

        this.exploitObject = exploitObject;
    }

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
