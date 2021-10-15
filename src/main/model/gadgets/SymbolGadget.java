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
        this.type = type;
        this.symbol = symbol;
    }

    // REQUIRES: list.size() == 3
    // EFFECTS: sets the base, type, and symbol of this object to the values in the list
    // MODIFIES: this
    public void fromList(List<String> list) {
        setBase(list.get(0));
        type = list.get(1);
        symbol = list.get(2);
    }

    // EFFECTS: Returns a python command to produce the gadget.
    public String getScript() {
        return "pack(" + getBase() + "." + type + "['" + symbol + "'])";
    }

    // EFFECTS: Returns the name and key properties of the gadget.
    public String getName() {
        return "SymbolGadget (" + getBase() + "." + type + "." + symbol + ")";
    }
}
