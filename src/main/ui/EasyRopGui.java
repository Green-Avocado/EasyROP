package ui;

import ui.contexts.ConsoleContext;
import ui.contexts.prompts.PayloadCreator;

import java.util.Scanner;

// EasyRop Application
public class EasyRopGui {
    private final Scanner scanner;
    private ConsoleContext context;

    // EFFECTS: Creates a new EasyRop application with a Scanner for input and an initial context for
    //          creating a Payload.
    public EasyRopGui() {
        scanner = new Scanner(System.in);
        context = new PayloadCreator();
        run();
    }

    // MODIFIES: this, this.context
    // EFFECTS: Prints the context string, then gets a line of user input and sends it to the handler of the context.
    private void run() {
        while (context != null) {
            System.out.print(context.getContextString());
            context = context.handleInput(scanner.nextLine());
        }
    }

    public static void main(String[] args) {
        new EasyRopGui();
    }
}
