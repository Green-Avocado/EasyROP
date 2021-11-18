package ui;

import ui.cli.ConsoleContext;
import ui.cli.prompts.PayloadCreator;

import java.util.Scanner;

// EasyRop Application with a command line interface
public class EasyRopCli {
    private final Scanner scanner;
    private ConsoleContext context;

    // EFFECTS: Creates a new EasyRop application with a Scanner for input and an initial context for
    //          creating a Payload.
    public EasyRopCli() {
        scanner = new Scanner(System.in);
        context = new PayloadCreator();
        run();
    }

    // EFFECTS: Starts the command line interface
    public static void main(String[] args) {
        new EasyRopCli();
    }

    // MODIFIES: this, this.context
    // EFFECTS: Prints the context string, then gets a line of user input and sends it to the handler of the context.
    private void run() {
        while (context != null) {
            System.out.print(context.getContextString());
            context = context.handleInput(scanner.nextLine());
        }
    }
}
