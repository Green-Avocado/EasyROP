package ui.cli.prompts;

import model.GadgetCollection;
import model.Payload;
import model.RopChain;
import persistence.JsonReader;
import persistence.TypeMismatchException;
import ui.cli.ConsoleContext;
import ui.cli.TextViewer;
import ui.cli.menus.CollectionEditor;
import ui.cli.menus.PayloadEditor;

import java.io.IOException;

// Represents a context where a user can select a file to load from.
public class LoadFile extends PromptContext {

    // EFFECTS: Creates a new LoadFile context with the given parentContext.
    //          The default filename is created based on the collection name of the parentContext.
    public LoadFile(ConsoleContext parentContext) {
        super(
                parentContext,
                "File name",
                ((CollectionEditor) parentContext).getCollection().getName() + ".json"
        );
    }

    // EFFECTS: Accepts user input and attempts to read the context of the specified file.
    //          If successful, returns the parentContext with the newly read data.
    //          If unsuccessful, returns the parentContext with the original data.
    @Override
    ConsoleContext handleInputInternal(String input) {
        try {
            GadgetCollection gadgetCollection = ((CollectionEditor) getParentContext()).getCollection();

            if (getParentContext().getClass() == PayloadEditor.class) {
                JsonReader jsonReader = new JsonReader("./data/payloads/" + input);
                jsonReader.payloadFromFile((Payload) gadgetCollection);
            } else {
                JsonReader jsonReader = new JsonReader("./data/ropchains/" + input);
                jsonReader.ropChainFromFile((RopChain) gadgetCollection);
            }

            return getParentContext();

        } catch (TypeMismatchException e) {
            return new TextViewer(getParentContext(), "Error: " + e.getMessage());
        } catch (IOException e) {
            return new TextViewer(getParentContext(), "Error: could not read file " + e.getMessage());
        }
    }
}
