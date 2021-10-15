package ui;

import ui.contexts.ConsoleContext;
import ui.contexts.prompts.collections.PayloadCreator;

import java.util.Scanner;

// EasyRop Application
public class EasyRopApp {
    private final Scanner scanner;
    private ConsoleContext context;

    // EFFECTS: Creates a new EasyRop application with a Scanner for input and an initial context for
    //          creating a Payload.
    public EasyRopApp() {
        scanner = new Scanner(System.in);
        context = new PayloadCreator();
        run();
    }

    // EFFECTS: Prints the context string, then gets a line of user input and sends it to the handler of the context.
    // MODIFIES: this, this.context
    private void run() {
        while (context != null) {
            System.out.print(context.getContextString());
            context = context.handleInput(scanner.nextLine());
        }
    }
}
