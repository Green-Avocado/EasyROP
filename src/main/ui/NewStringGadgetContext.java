package ui;

import model.gadgets.StringGadget;

public class NewStringGadgetContext extends PromptContext {
    private String base;

    public NewStringGadgetContext(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public NewStringGadgetContext(ConsoleContext parentContext, String base) {
        super(parentContext, "String", "/bin/sh\\x00");

        this.base = base;
    }

    ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new NewStringGadgetContext(getParentContext(), input);
        } else {
            return new NewIndexContext(getParentContext(), new StringGadget(base, input));
        }
    }
}