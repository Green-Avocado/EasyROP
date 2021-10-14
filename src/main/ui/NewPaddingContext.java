package ui;

import model.Padding;

public class NewPaddingContext extends PromptContext {
    public NewPaddingContext(ConsoleContext parentContext) {
        super(parentContext, "Padding length", "8");
    }

    ConsoleContext handleInputInternal(String input) {
        int length = Integer.parseInt(input);

        if (length < 0) {
            return getParentContext();
        }

        return new NewIndexContext(getParentContext(), new Padding(length));
    }
}
