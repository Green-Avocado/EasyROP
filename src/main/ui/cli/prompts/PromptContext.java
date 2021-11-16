package ui.cli.prompts;

import ui.cli.ConsoleContext;

// Represents a UI context where a value is inputted or the default value is selected if left blank.
public abstract class PromptContext extends ConsoleContext {
    private String prompt;
    private String defaultResponse;

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
    @Override
    public String getContextString() {
        return prompt + " (default " + defaultResponse + "): ";
    }

    // EFFECTS: If input is not empty, passes the input to an abstract input handling method and returns its context.
    //          If input is empty, passes the default value to the abstract input handling method instead.
    @Override
    public ConsoleContext handleInput(String input) {
        if (!input.isEmpty()) {
            return handleInputInternal(input);
        } else {
            return handleInputInternal(defaultResponse);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the prompt to the specified string.
    void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // MODIFIES: this
    // EFFECTS: Sets the defaultResponse to the specified string.
    void setDefaultResponse(String defaultResponse) {
        this.defaultResponse = defaultResponse;
    }

    // EFFECTS: Handles the input after the defaultResponse has been applied if necessary, returns a new ConsoleContext
    abstract ConsoleContext handleInputInternal(String input);
}
