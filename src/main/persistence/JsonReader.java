package persistence;

import model.ExploitObject;
import model.ExploitObjectType;
import model.Payload;
import model.RopChain;
import model.gadgets.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

// Represents a reader that reads a Payload or RopChain from a json file.
public class JsonReader {
    private final String filename;

    // EFFECTS: Creates a new JsonReader with a given filename.
    public JsonReader(String filename) {
        this.filename = filename;
    }

    // MODIFIES: payload
    // EFFECTS: Replaces the contents of payload with the contents of the file
    //          Throws TypeMismatchException if JSON data is malformed or of the wrong type.
    //          Throws IOException on error reading the file.
    public void payloadFromFile(Payload payload) throws TypeMismatchException, IOException {
        payloadFromJson(payload, jsonFromFile());
    }

    // MODIFIES: ropChain
    // EFFECTS: Replaces the contents of ropChain with the contents of the file
    //          Throws TypeMismatchException if JSON data is malformed or of the wrong type.
    //          Throws IOException on error reading the file.
    public void ropChainFromFile(RopChain ropChain) throws TypeMismatchException, IOException {
        ropChainFromJson(ropChain, jsonFromFile());
    }

    // EFFECTS: Returns a JSONObject from file specified by filename.
    //          Throws IOException on error reading the file.
    private JSONObject jsonFromFile() throws IOException {
        String jsonString = String.join("\n", Files.readAllLines(Paths.get(filename)));
        return new JSONObject(jsonString);
    }

    // MODIFIES: payload
    // EFFECTS: Replaces the contents of payload with the contents of jsonObject
    //          Throws TypeMismatchException if JSON data is malformed.
    private void payloadFromJson(Payload payload, JSONObject jsonObject) throws TypeMismatchException {
        ExploitObjectType type = jsonObject.getEnum(ExploitObjectType.class, "type");
        if (type != ExploitObjectType.PAYLOAD) {
            throw new TypeMismatchException("Payload cannot be of type " + type.name());
        }

        payload.setName(jsonObject.getString("name"));
        payload.clear();

        for (Object object : jsonObject.getJSONArray("exploitObjectList")) {
            RopChain ropChain = new RopChain();
            ropChainFromJson(ropChain, (JSONObject) object);
            payload.add(ropChain, payload.getLength());
        }
    }

    // MODIFIES: ropChain
    // EFFECTS: Replaces the contents of ropChain with the contents of jsonObject
    //          Throws TypeMismatchException if JSON data is malformed.
    private void ropChainFromJson(RopChain ropChain, JSONObject jsonObject) throws TypeMismatchException {
        ExploitObjectType type = jsonObject.getEnum(ExploitObjectType.class, "type");
        if (type != ExploitObjectType.ROP_CHAIN) {
            throw new TypeMismatchException("RopChain cannot be of type " + type.name());
        }

        ropChain.setName(jsonObject.getString("name"));
        ropChain.clear();

        for (Object object : jsonObject.getJSONArray("exploitObjectList")) {
            ropChain.add(gadgetFromJson((JSONObject) object), ropChain.getLength());
        }
    }

    // EFFECTS: Returns a Padding or Gadget object from a given JSONObject.
    //          Throws TypeMismatchException if JSON data is malformed.
    private ExploitObject gadgetFromJson(JSONObject jsonObject) throws TypeMismatchException {
        ExploitObjectType type = jsonObject.getEnum(ExploitObjectType.class, "type");
        switch (type) {
            case PADDING:
                return paddingFromJson(jsonObject);
            case ADDRESS_GADGET:
                return addressGadgetFromJson(jsonObject);
            case INSTRUCTIONS_GADGET:
                return instructionsGadgetFromJson(jsonObject);
            case STRING_GADGET:
                return stringGadgetFromJson(jsonObject);
            case SYMBOL_GADGET:
                return symbolGadgetFromJson(jsonObject);
            default:
                throw new TypeMismatchException("Gadget cannot be of type " + type.name());
        }
    }

    // EFFECTS: Returns a Padding object from a given JSONObject.
    private Padding paddingFromJson(JSONObject jsonObject) {
        Padding padding = new Padding();
        padding.setLength(jsonObject.getString("length"));
        return padding;
    }

    // EFFECTS: Returns an addressGadget object from a given JSONObject.
    private AddressGadget addressGadgetFromJson(JSONObject jsonObject) {
        AddressGadget addressGadget = new AddressGadget();
        addressGadget.setBase(jsonObject.getString("base"));
        addressGadget.setOffset(jsonObject.getString("offset"));
        return addressGadget;
    }

    // EFFECTS: Returns an InstructionsGadget object from a given JSONObject.
    private InstructionsGadget instructionsGadgetFromJson(JSONObject jsonObject) {
        InstructionsGadget instructionsGadget = new InstructionsGadget();
        instructionsGadget.setBase(jsonObject.getString("base"));

        ArrayList<String> instructions = new ArrayList<>();
        for (Object object : jsonObject.getJSONArray("instructions")) {
            instructions.add((String) object);
        }

        instructionsGadget.setInstructions(instructions);

        return instructionsGadget;
    }

    // EFFECTS: Returns a StringGadget object from a given JSONObject.
    private StringGadget stringGadgetFromJson(JSONObject jsonObject) {
        StringGadget stringGadget = new StringGadget();
        stringGadget.setBase(jsonObject.getString("base"));
        stringGadget.setString(jsonObject.getString("string"));
        return stringGadget;
    }

    // EFFECTS: Returns a SymbolGadget object from a given JSONObject.
    private SymbolGadget symbolGadgetFromJson(JSONObject jsonObject) {
        SymbolGadget symbolGadget = new SymbolGadget();
        symbolGadget.setBase(jsonObject.getString("base"));
        symbolGadget.setType(jsonObject.getString("symbolType"));
        symbolGadget.setSymbol(jsonObject.getString("symbol"));
        return symbolGadget;
    }
}
