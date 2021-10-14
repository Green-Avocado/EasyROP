package ui.contexts.menus;

import ui.contexts.ConsoleContext;
import ui.contexts.prompts.gadgets.*;

import java.util.Arrays;
import java.util.List;

public class GadgetCreator extends MenuContext {

    public GadgetCreator(ConsoleContext parentContext) {
        super(parentContext);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "":
            case "p":
                return new NewPaddingContext(getParentContext());
            case "a":
                return new NewAddressGadgetContext(getParentContext());
            case "i":
                return new NewInstructionsGadgetContext(getParentContext());
            case "s":
                return new NewStringGadgetContext(getParentContext());
            case "sy":
                return new NewSymbolGadgetContext(getParentContext());
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
