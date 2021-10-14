package ui;

import model.Padding;

public class NewPaddingContext extends PromptContext {
    private Padding padding;

    public NewPaddingContext(ConsoleContext parentContext) {
        super(parentContext, "Padding length", "8");
    }

    public NewPaddingContext(ConsoleContext parentContext, Padding padding) {
        super(parentContext, "Index", String.valueOf(((RopChainEditor) parentContext).getCollection().getLength()));

        this.padding = padding;
    }

    ConsoleContext handleInputInternal(String input) {
        int num = Integer.parseInt(input);

        if (num < 0) {
            return getParentContext();
        }

        if (padding == null) {
            return readLength(num);
        } else {
            return readIndex(num);
        }
    }

    ConsoleContext readLength(int length) {
        return new NewPaddingContext(getParentContext(), new Padding(length));
    }

    ConsoleContext readIndex(int index) {
        ((RopChainEditor) getParentContext()).getCollection().add(padding, index);
        return getParentContext();
    }
}
