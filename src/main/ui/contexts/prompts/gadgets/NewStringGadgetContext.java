package ui.contexts.prompts.gadgets;

import model.gadgets.StringGadget;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PromptContext;
import ui.contexts.prompts.util.NewIndexContext;

public class NewStringGadgetContext extends PromptContext {
    private String base;

    public NewStringGadgetContext(ConsoleContext parentContext) {
        super(parentContext, "Base", "exe");
    }

    public NewStringGadgetContext(ConsoleContext parentContext, String base) {
        super(parentContext, "String", "/bin/sh\\x00");

        this.base = base;
    }

    public ConsoleContext handleInputInternal(String input) {
        if (base == null) {
            return new NewStringGadgetContext(getParentContext(), input);
        } else {
            return new NewIndexContext(getParentContext(), new StringGadget(base, input));
        }
    }
}
