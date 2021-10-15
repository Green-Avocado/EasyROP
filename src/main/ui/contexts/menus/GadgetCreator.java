package ui.contexts.menus;

import model.gadgets.*;
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
        ExploitObject gadget;
        ArrayList<List<String>> promptData = new ArrayList<>();

        switch (input.toLowerCase()) {
            case "":
            case "p":
                gadget = newPadding(promptData);
                break;

            case "a":
                gadget = newAddressGadget(promptData);
                break;

            case "i":
                return new InstructionsGadgetCreator(getParentContext());

            case "s":
                gadget = newStringGadget(promptData);
                break;

            case "sy":
                gadget = newSymbolGadget(promptData);
                break;

            case "q":
                return getParentContext();

            default:
                return this;
        }

        return new ExploitObjectCreator(getParentContext(), gadget, promptData);
    }

    ExploitObject newPadding(ArrayList<List<String>> promptData) {
        promptData.add(Arrays.asList("Length", "8"));
        return new Padding();
    }

    ExploitObject newAddressGadget(ArrayList<List<String>> promptData) {
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Offset", "0"));
        return new AddressGadget();
    }

    ExploitObject newStringGadget(ArrayList<List<String>> promptData) {
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("String", "/bin/sh\\x00"));
        return new StringGadget();
    }

    ExploitObject newSymbolGadget(ArrayList<List<String>> promptData) {
        promptData.add(Arrays.asList("Base", "exe"));
        promptData.add(Arrays.asList("Type", "sym"));
        promptData.add(Arrays.asList("Symbol", "main"));
        return new SymbolGadget();
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
