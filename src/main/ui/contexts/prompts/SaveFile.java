package ui.contexts.prompts;

import persistence.JsonWriter;
import ui.contexts.ConsoleContext;
import ui.contexts.TextViewer;
import ui.contexts.menus.CollectionEditor;

// Represents a context where the user can select a file to save to.
public class SaveFile extends PromptContext {

    // EFFECTS: Creates a new SaveFile context with the given parentContext.
    //          The default filename is created based on the collection name of the parentContext.
    public SaveFile(ConsoleContext parentContext) {
        super(
                parentContext,
                "File name",
                "./data/" + ((CollectionEditor) parentContext).getCollection().getName() + ".json"
        );
    }

    // EFFECTS: Accepts user input and attempts to write the contents of the collection to the specified file.
    //          Returns the parentContext.
    @Override
    ConsoleContext handleInputInternal(String input) {
        try {
            JsonWriter jsonWriter = new JsonWriter(input);
            jsonWriter.writeObject(((CollectionEditor) getParentContext()).getCollection());
            return getParentContext();
        } catch (Exception e) {
            return new TextViewer(getParentContext(), "Error: could not save to file " + e.getMessage());
        }
    }
}
