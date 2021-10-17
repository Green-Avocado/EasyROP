package ui.contexts.menus;

import model.gadgets.AddressGadget;
import model.gadgets.Padding;
import model.gadgets.StringGadget;
import model.gadgets.SymbolGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.ExploitObjectCreator;
import ui.contexts.prompts.InstructionsGadgetCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a UI context that creates a Gadget or Padding object
public class GadgetCreator extends MenuContext {

    // EFFECTS: Returns a new GadgetCreator context with the given parentContext.
    public GadgetCreator(ConsoleContext parentContext) {
        super(parentContext);
    }

    // EFFECTS: Calls a helper function and returns its context depending on the menu option selected.
    //          If no valid option was selected, returns this context.
    public ConsoleContext handleInput(String input) {
        switch (input.toLowerCase()) {
            case "":
            case "p":
                return newPadding();

            case "a":
                return newAddressGadget();

            case "i":
                return new InstructionsGadgetCreator(getParentContext());

            case "s":
                return newStringGadget();

            case "sy":
                return newSymbolGadget();

            case "q":
                return getParentContext();

            default:
                return this;
        }
    }

    // EFFECTS: Returns an ExploitObjectCreator context with parameters for adding a new Padding object.
    ConsoleContext newPadding() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Length", "8"));
        return new ExploitObjectCreator(getParentContext(), new Padding(), promptData);
    }

    // EFFECTS: Returns an ExploitObjectCreator context with parameters for adding a new AddressGadget object.
    ConsoleContext newAddressGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Offset", "0"));
        return new ExploitObjectCreator(getParentContext(), new AddressGadget(), promptData);
    }

    // EFFECTS: Returns an ExploitObjectCreator context with parameters for adding a new StringGadget object.
    ConsoleContext newStringGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("String", "/bin/sh\\x00"));
        return new ExploitObjectCreator(getParentContext(), new StringGadget(), promptData);
    }

    // EFFECTS: Returns an ExploitObjectCreator context with parameters for adding a new SymbolGadget object.
    ConsoleContext newSymbolGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Type", "sym"));
        promptData.add(Arrays.asList("Symbol", "main"));
        return new ExploitObjectCreator(getParentContext(), new SymbolGadget(), promptData);
    }

    // EFFECTS: Returns the list of menu options.
    List<String> getMenu() {
        return Arrays.asList(
                "[P]adding",
                "[a]ddress gadget",
                "[i]instructions gadget",
                "[s]tring gadget",
                "[sy]mbol gadget",
                "[q]uit"
        );
    }
}
