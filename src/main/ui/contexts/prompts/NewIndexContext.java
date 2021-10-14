package ui.contexts.prompts;

import model.gadgets.ExploitObject;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;

public class NewIndexContext extends PromptContext {
    private final ExploitObject exploitObject;

    public NewIndexContext(ConsoleContext parentContext, ExploitObject exploitObject) {
        super(parentContext, "Index", String.valueOf(((CollectionEditor) parentContext).getCollection().getLength()));

        this.exploitObject = exploitObject;
    }

    ConsoleContext handleInputInternal(String input) {
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
