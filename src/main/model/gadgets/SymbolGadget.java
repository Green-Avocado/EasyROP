package model.gadgets;

public class SymbolGadget extends Gadget {
    private String symbol;
    private String type;

    // EFFECTS: creates a new SymbolGadget
    public SymbolGadget() {
        super();
    }

    // EFFECTS: returns a python command to produce the gadget
    public String getScript() {
        return "pack(" + getBase() + "." + type + "['" + symbol + "'])";
    }

    // EFFECTS: returns the name and key properties of the gadget
    public String getName() {
        return "SymbolGadget (" + getBase() + "." + type + "." + symbol + ")";
    }

    // MODIFIES: this
    // EFFECTS: sets the value of the symbol
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // EFFECTS: returns the value of the symbol
    public String getSymbol() {
        return symbol;
    }

    // MODIFIES: this
    public void setType(String type) {
        this.type = type;
    }

    // EFFECTS: returns the type of the symbol
    public String getType() {
        return type;
    }
}