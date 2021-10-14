package ui;

import ui.contexts.ConsoleContext;
import ui.contexts.prompts.collections.PayloadCreator;

import java.util.Scanner;

// EasyRop Application
public class EasyRopApp {
    private final Scanner scanner;
    private ConsoleContext context;

    public EasyRopApp() {
        scanner = new Scanner(System.in);
        context = new PayloadCreator();
        run();
    }

    private void run() {
        while (context != null) {
            System.out.print(context.getContextString());
            context = context.handleInput(scanner.nextLine());
        }
    }
}
