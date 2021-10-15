package ui.contexts.prompts;

import ui.contexts.ConsoleContext;

// Represents a UI context where a value is inputted or the default value is selected if left blank.
public abstract class PromptContext extends ConsoleContext {
    private final String prompt;
    private final String defaultResponse;

    // EFFECTS: Creates a new PromptContext with a given parentContext, prompt string, and default response.
    public PromptContext(
            ConsoleContext parentContext,
            String prompt,
            String defaultResponse
    ) {
        super(parentContext);

        this.prompt = prompt;
        this.defaultResponse = defaultResponse;
    }

    // EFFECTS: Returns the formatted prompt string and default response.
    public String getContextString() {
        return prompt + " (default " + defaultResponse + "): ";
    }

    // EFFECTS: If input is not empty, passes the input to an abstract input handling method and returns its context.
    //          If input is empty, passes the default value to the abstract input handling method instead.
    public ConsoleContext handleInput(String input) {
        if (!input.isEmpty()) {
            return handleInputInternal(input);
        } else {
            return handleInputInternal(defaultResponse);
        }
    }

    public abstract ConsoleContext handleInputInternal(String input);
}
