package ui.contexts.prompts;

import persistence.JsonWriter;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;

public class SaveFile extends PromptContext {

    public SaveFile(ConsoleContext parentContext) {
        super(
                parentContext,
                "File name",
                "./data/" + ((CollectionEditor) parentContext).getCollection().getName() + ".json"
        );
    }

    @Override
    ConsoleContext handleInputInternal(String input) {
        try {
            JsonWriter jsonWriter = new JsonWriter(input);
            jsonWriter.writeObject(((CollectionEditor) getParentContext()).getCollection());
            return getParentContext();
        } catch (Exception e) {
            return getParentContext();
        }
    }
}
