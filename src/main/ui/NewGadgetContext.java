package ui;

import java.util.Arrays;
import java.util.List;

public class NewGadgetContext extends MenuContext {

    public NewGadgetContext(ConsoleContext parentContext) {
        super(parentContext);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "":
            case "p":
                return new NewPaddingContext(getParentContext());
            case "a":
                return this;
            case "i":
                return this;
            case "s":
                return this;
            case "sy":
                return this;
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
