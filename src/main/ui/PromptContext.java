package ui;

public abstract class PromptContext extends ConsoleContext {
    private final String prompt;
    private final String defaultResponse;

    public PromptContext(
            ConsoleContext parentContext,
            String prompt,
            String defaultResponse
    ) {
        super(parentContext);

        this.prompt = prompt;
        this.defaultResponse = defaultResponse;
    }

    public String getContextString() {
        return prompt + " (default " + defaultResponse + "): ";
    }

    ConsoleContext handleInput(String input) {
        if (!input.isEmpty()) {
            return handleInputInternal(input);
        } else {
            return handleInputInternal(defaultResponse);
        }
    }

    abstract ConsoleContext handleInputInternal(String input);
}
