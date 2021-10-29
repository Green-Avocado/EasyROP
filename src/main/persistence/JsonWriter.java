package persistence;

import model.ExploitObject;
import model.ExploitObjectType;
import model.GadgetCollection;
import model.gadgets.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

// Represents a writer that writes the contents of a Payload or RopChain to a json file.
public class JsonWriter {
    private final String filename;

    // EFFECTS: Creates a new JsonWriter with the given filename.
    public JsonWriter(String filename) {
        this.filename = filename;
    }

    // EFFECTS: Writes an exploitObject to the file specified by filename.
    //          Throws IOException if unsuccessful.
    public void writeObject(ExploitObject exploitObject) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        exploitObjectToJson(exploitObject).write(fileWriter, 4, 0);
        fileWriter.close();
    }

    // EFFECTS: Returns a JSONObject with the contents of the given exploitObject.
    private JSONObject exploitObjectToJson(ExploitObject exploitObject) {
        JSONObject jsonObject = new JSONObject();

        ExploitObjectType type = exploitObject.getExploitObjectType();
        jsonObject.put("type", type);

        switch (type) {
            default:
                return collectionJson(jsonObject, (GadgetCollection) exploitObject);
            case PADDING:
                return paddingJson(jsonObject, (Padding) exploitObject);
            case ADDRESS_GADGET:
                return addressJson(jsonObject, (AddressGadget) exploitObject);
            case INSTRUCTIONS_GADGET:
                return instructionsJson(jsonObject, (InstructionsGadget) exploitObject);
            case STRING_GADGET:
                return stringJson(jsonObject, (StringGadget) exploitObject);
            case SYMBOL_GADGET:
                return symbolJson(jsonObject, (SymbolGadget) exploitObject);
        }
    }

    // EFFECTS: Returns a JSONObject with the contents of the given gadgetCollection.
    private JSONObject collectionJson(JSONObject jsonObject, GadgetCollection gadgetCollection) {
        jsonObject.put("name", gadgetCollection.getName());
        jsonObject.put("exploitObjectList", new JSONArray());

        for (ExploitObject exploitObject : gadgetCollection.getList()) {
            jsonObject.append("exploitObjectList", exploitObjectToJson(exploitObject));
        }

        return jsonObject;
    }

    // EFFECTS: Returns a JSONObject with the contents of the given padding.
    private JSONObject paddingJson(JSONObject jsonObject, Padding padding) {
        jsonObject.put("length", padding.getLength());
        return jsonObject;
    }

    // EFFECTS: Returns a JSONObject with the contents of the given addressGadget.
    private JSONObject addressJson(JSONObject jsonObject, AddressGadget addressGadget) {
        jsonObject.put("base", addressGadget.getBase());
        jsonObject.put("offset", addressGadget.getOffset());
        return jsonObject;
    }

    // EFFECTS: Returns a JSONObject with the contents of the given instructionsGadget.
    private JSONObject instructionsJson(JSONObject jsonObject, InstructionsGadget instructionsGadget) {
        jsonObject.put("base", instructionsGadget.getBase());

        for (String instruction : instructionsGadget.getInstructions()) {
            jsonObject.append("instructions", instruction);
        }

        return jsonObject;
    }

    // EFFECTS: Returns a JSONObject with the contents of the given stringGadget.
    private JSONObject stringJson(JSONObject jsonObject, StringGadget stringGadget) {
        jsonObject.put("base", stringGadget.getBase());
        jsonObject.put("string", stringGadget.getString());
        return jsonObject;
    }

    // EFFECTS: Returns a JSONObject with the contents of the given symbolGadget.
    private JSONObject symbolJson(JSONObject jsonObject, SymbolGadget symbolGadget) {
        jsonObject.put("base", symbolGadget.getBase());
        jsonObject.put("symbolType", symbolGadget.getType());
        jsonObject.put("symbol", symbolGadget.getSymbol());
        return jsonObject;
    }
}
