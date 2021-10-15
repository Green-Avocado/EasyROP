package ui.contexts.menus;

import model.gadgets.AddressGadget;
import model.gadgets.Padding;
import model.gadgets.StringGadget;
import model.gadgets.SymbolGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.InstructionsGadgetCreator;
import ui.contexts.prompts.util.ExploitObjectCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a UI context that creates a Gadget or Padding object
public class GadgetCreator extends MenuContext {

    public GadgetCreator(ConsoleContext parentContext) {
        super(parentContext);
    }

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

    ConsoleContext newPadding() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Length", "8"));
        return new ExploitObjectCreator(getParentContext(), new Padding(), promptData);
    }

    ConsoleContext newAddressGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Offset", "0"));
        return new ExploitObjectCreator(getParentContext(), new AddressGadget(), promptData);
    }

    ConsoleContext newStringGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("String", "/bin/sh\\x00"));
        return new ExploitObjectCreator(getParentContext(), new StringGadget(), promptData);
    }

    ConsoleContext newSymbolGadget() {
        ArrayList<List<String>> promptData = new ArrayList<>();
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Type", "sym"));
        promptData.add(Arrays.asList("Symbol", "main"));
        return new ExploitObjectCreator(getParentContext(), new SymbolGadget(), promptData);
    }

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
