package ui;

import model.GadgetCollection;

public abstract class PromptContext extends ConsoleContext {
    private final String prompt;
    private final String defaultResponse;

    public PromptContext(
            GadgetCollection collection,
            String prompt,
            String defaultResponse
    ) {
        super(collection);

        this.prompt = prompt;
        this.defaultResponse = defaultResponse;
    }

    public String getContextString() {
        return prompt + " (default " + defaultResponse + "): ";
    }

    String handleInputInternal(String input) {
        if (!input.isEmpty()) {
            return input;
        } else {
            return defaultResponse;
        }
    }
}
