package model.gadgets;

import java.util.List;

// Represents the memory address of a given symbol in an ELF file.
public class SymbolGadget extends Gadget {
    private String type;
    private String symbol;

    // EFFECTS: Creates a new SymbolGadget.
    public SymbolGadget() {
        super();
    }

    // EFFECTS: Creates a new SymbolGadget with a specified base, type, and symbol.
    public SymbolGadget(String base, String type, String symbol) {
        super(base);
        setType(type);
        setSymbol(symbol);
    }

    // REQUIRES: list.size() == 3
    // EFFECTS: sets the base, type, and symbol of this object to the values in the list
    // MODIFIES: this
    public void fromList(List<String> list) {
        setBase(list.get(0));
        setType(list.get(1));
        setSymbol(list.get(2));
    }

    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "pack(" + getBase() + "." + getType() + "['" + getSymbol() + "'])";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "SymbolGadget (" + getBase() + "." + getType() + "." + getSymbol() + ")";
    }

    // EFFECTS: Sets the symbol type of this object to the specified string.
    // MODIFIES: this
    public void setType(String type) {
        this.type = type;
    }

    // EFFECTS: Returns the symbol type of this object.
    public String getType() {
        return type;
    }

    // EFFECTS: Sets the symbol value of this object to the specified string.
    // MODIFIES: this
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // EFFECTS: Returns the symbol value of this object.
    public String getSymbol() {
        return symbol;
    }
}
