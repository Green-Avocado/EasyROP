package persistence;

import model.ExploitObject;
import model.ExploitObjectType;
import model.GadgetCollection;
import model.gadgets.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    private final String filename;

    public JsonWriter(String filename) {
        this.filename = filename;
    }

    public void writeObject(ExploitObject exploitObject) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        exploitObjectToJson(exploitObject).write(fileWriter);
        fileWriter.close();
    }

    private JSONObject exploitObjectToJson(ExploitObject exploitObject) {
        JSONObject jsonObject = new JSONObject();

        ExploitObjectType type = exploitObject.getExploitObjectType();
        jsonObject.append("type", type);

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

    private JSONObject collectionJson(JSONObject jsonObject, GadgetCollection gadgetCollection) {
        jsonObject.append("name", gadgetCollection.getName());

        JSONArray jsonArray = new JSONArray();

        for (ExploitObject exploitObject : gadgetCollection.getList()) {
            jsonArray.put(exploitObjectToJson(exploitObject));
        }

        jsonObject.append("exploitObjectList", jsonArray);

        return jsonObject;
    }

    private JSONObject paddingJson(JSONObject jsonObject, Padding padding) {
        jsonObject.append("length", padding.getLength());
        return jsonObject;
    }

    private JSONObject addressJson(JSONObject jsonObject, AddressGadget addressGadget) {
        jsonObject.append("base", addressGadget.getBase());
        jsonObject.append("offset", addressGadget.getOffset());
        return jsonObject;
    }

    private JSONObject instructionsJson(JSONObject jsonObject, InstructionsGadget instructionsGadget) {
        jsonObject.append("base", instructionsGadget.getBase());

        JSONArray jsonArray = new JSONArray();

        for (String instruction : instructionsGadget.getInstructions()) {
            jsonArray.put(instruction);
        }

        jsonObject.append("instructions", jsonArray);

        return jsonObject;
    }

    private JSONObject stringJson(JSONObject jsonObject, StringGadget stringGadget) {
        jsonObject.append("base", stringGadget.getBase());
        jsonObject.append("string", stringGadget.getString());
        return jsonObject;
    }

    private JSONObject symbolJson(JSONObject jsonObject, SymbolGadget symbolGadget) {
        jsonObject.append("base", symbolGadget.getBase());
        jsonObject.append("symbolType", symbolGadget.getType());
        jsonObject.append("symbol", symbolGadget.getSymbol());
        return jsonObject;
    }
}
