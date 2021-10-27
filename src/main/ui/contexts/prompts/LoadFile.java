package ui.contexts.prompts;

import persistence.JsonReader;
import ui.contexts.ConsoleContext;
import ui.contexts.menus.CollectionEditor;
import ui.contexts.menus.PayloadEditor;
import ui.contexts.menus.RopChainEditor;

public class LoadFile extends PromptContext {

    public LoadFile(ConsoleContext parentContext) {
        super(
                parentContext,
                "File name",
                "./data/" + ((CollectionEditor) parentContext).getCollection().getName() + ".json"
        );
    }

    @Override
    ConsoleContext handleInputInternal(String input) {
        try {
            JsonReader jsonReader = new JsonReader(input);

            if (getParentContext().getClass() == PayloadEditor.class) {
                return new PayloadEditor(jsonReader.payloadFromFile());
            } else {
                return new RopChainEditor(jsonReader.ropChainFromFile(), getParentContext());
            }
        } catch (Exception e) {
            return getParentContext();
        }
    }
}
