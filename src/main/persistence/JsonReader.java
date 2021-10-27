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

    // EFFECTS: Returns a Payload object from the file specified by filename.
    //          Throws UnsupportedOperationException if JSON data is malformed or of the wrong type.
    //          Throws IOException on error reading the file.
    public Payload payloadFromFile() throws UnsupportedOperationException, IOException {
        return payloadFromJson(jsonFromFile());
    }

    // EFFECTS: Returns a RopChain object from the file specified by filename.
    //          Throws UnsupportedOperationException if JSON data is malformed or of the wrong type.
    //          Throws IOException on error reading the file.
    public RopChain ropChainFromFile() throws UnsupportedOperationException, IOException {
        return ropChainFromJson(jsonFromFile());
    }

    // EFFECTS: Returns a JSONObject from file specified by filename.
    //          Throws IOException on error reading the file.
    private JSONObject jsonFromFile() throws IOException {
        String jsonString = String.join("\n", Files.readAllLines(Paths.get(filename)));
        return new JSONObject(jsonString);
    }

    // EFFECTS: Returns a Payload object from a given JSONObject.
    //          Throws UnsupportedOperationException if JSON data is malformed.
    private Payload payloadFromJson(JSONObject jsonObject) throws UnsupportedOperationException {
        ExploitObjectType type = jsonObject.getEnum(ExploitObjectType.class, "type");
        if (type != ExploitObjectType.PAYLOAD) {
            throw new UnsupportedOperationException(type.name());
        }

        Payload payload = new Payload();
        payload.setName(jsonObject.getString("name"));

        for (Object object : jsonObject.getJSONArray("exploitObjectList")) {
            payload.add(ropChainFromJson((JSONObject) object), payload.getLength());
        }

        return payload;
    }

    // EFFECTS: Returns a RopChain object from a given JSONObject.
    //          Throws UnsupportedOperationException if JSON data is malformed.
    private RopChain ropChainFromJson(JSONObject jsonObject) throws UnsupportedOperationException {
        ExploitObjectType type = jsonObject.getEnum(ExploitObjectType.class, "type");
        if (type != ExploitObjectType.ROP_CHAIN) {
            throw new UnsupportedOperationException(type.name());
        }

        RopChain ropChain = new RopChain();
        ropChain.setName(jsonObject.getString("name"));

        for (Object object : jsonObject.getJSONArray("exploitObjectList")) {
            ropChain.add(gadgetFromJson((JSONObject) object), ropChain.getLength());
        }

        return ropChain;
    }

    // EFFECTS: Returns a Padding or Gadget object from a given JSONObject.
    //          Throws UnsupportedOperationException if JSON data is malformed.
    private ExploitObject gadgetFromJson(JSONObject jsonObject) throws UnsupportedOperationException {
        switch (jsonObject.getEnum(ExploitObjectType.class, "type")) {
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
                throw new UnsupportedOperationException();
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
