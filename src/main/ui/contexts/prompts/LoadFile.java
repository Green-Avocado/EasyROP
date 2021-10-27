package ui.contexts.prompts;

import persistence.JsonReader;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

// Represents a context where a user can select a file to load from.
public class LoadFile extends PromptContext {

    // EFFECTS: Creates a new LoadFile context with the given parentContext.
    //          The default filename is created based on the collection name of the parentContext.
    public LoadFile(ConsoleContext parentContext) {
        super(
                parentContext,
                "File name",
                "./data/" + ((CollectionEditor) parentContext).getCollection().getName() + ".json"
        );
    }

    // EFFECTS: Accepts user input and attempts to read the context of the specified file.
    //          If successful, returns a new context of the same class as the parentContext with the new data.
    //          If unsuccessful, returns the parentContext with the original data.
    @Override
    ConsoleContext handleInputInternal(String input) {
        try {
            JsonReader jsonReader = new JsonReader(input);

            if (getParentContext().getClass() == PayloadEditor.class) {
                return new PayloadEditor(jsonReader.payloadFromFile());
            } else {
                return new RopChainEditor(getParentContext(), jsonReader.ropChainFromFile());
            }
        } catch (Exception e) {
            return getParentContext();
        }
    }
}
