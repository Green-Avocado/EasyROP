package model.gadgets;

public class SymbolGadget extends Gadget {
    private final String type;
    private final String symbol;

    // EFFECTS: creates a new SymbolGadget
    public SymbolGadget(String base, String type, String symbol) {
        super(base);
        this.type = type;
        this.symbol = symbol;
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + "." + type + "['" + symbol + "'])";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "SymbolGadget (" + getBase() + "." + type + "." + symbol + ")";
    }
}
