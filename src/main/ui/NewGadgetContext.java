package ui;

import model.ExploitObject;

import java.util.Arrays;
import java.util.List;

public class NewGadgetContext extends MenuContext {

    public NewGadgetContext(ConsoleContext parentContext) {
        super(parentContext);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            default:
            case "n":
                return null;
            case "q":
                return getParentContext();
        }
    }

    List<String> getMenu() {
        return Arrays.asList(
                "[N]ew ROPchain",
                "[o]pen ROPchain",
                "[m]move ROPchain",
                "[d]elete ROPchain",
                "[p]rint ROPchain"
        );
    }
}
