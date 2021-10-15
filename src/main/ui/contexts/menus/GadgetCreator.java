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
        input = input.toLowerCase();
        ExploitObject gadget;
        ArrayList<List<String>> promptData = new ArrayList<>();

        switch (input) {
            case "":
            case "p":
                gadget = new Padding();
                promptData.add(Arrays.asList("Length", "8"));
                break;
            case "a":
                gadget = new AddressGadget();
                promptData.add(Arrays.asList("Base", "exe"));
                promptData.add(Arrays.asList("Offset", "0"));
                break;
            case "i":
                return new InstructionsGadgetCreator(getParentContext());
            case "s":
                gadget = new StringGadget();
                promptData.add(Arrays.asList("Base", "exe"));
                promptData.add(Arrays.asList("String", "/bin/sh\\x00"));
                break;
            case "sy":
                gadget = new SymbolGadget();
                promptData.add(Arrays.asList("Base", "exe"));
                promptData.add(Arrays.asList("Type", "sym"));
                promptData.add(Arrays.asList("Symbol", "main"));
                break;
            case "q":
                return getParentContext();
            default:
                return this;
        }

        return new ExploitObjectCreator(getParentContext(), gadget, promptData);
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
