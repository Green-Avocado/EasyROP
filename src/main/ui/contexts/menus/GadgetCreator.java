package ui.contexts.menus;

import ui.contexts.ConsoleContext;
import ui.contexts.prompts.gadgets.*;

import java.util.Arrays;
import java.util.List;

// Represents a UI context that creates a Gadget or Padding object
public class GadgetCreator extends MenuContext {

    public GadgetCreator(ConsoleContext parentContext) {
        super(parentContext);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "":
            case "p":
                return new PaddingCreator(getParentContext());
            case "a":
                return new AddressGadgetCreator(getParentContext());
            case "i":
                return new InstructionsGadgetCreator(getParentContext());
            case "s":
                return new StringGadgetCreator(getParentContext());
            case "sy":
                return new SymbolGadgetCreator(getParentContext());
            case "q":
                return getParentContext();
            default:
                return this;
        }
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
