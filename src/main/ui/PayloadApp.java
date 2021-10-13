package ui;

import model.ExploitObject;
import model.Payload;
import model.RopChain;

import java.util.Scanner;

public class PayloadApp {
    private static final String PAYLOAD_DEFAULT_NAME = "payload";
    private static final String ROPCHAIN_DEFAULT_NAME = "ropChain";

    private Payload payload;
    private final Scanner scanner;

    public PayloadApp() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        run();
    }

    private void run() {
        resetPayload();

        while (true) {
            displayPayloadMenu();

            switch (scanner.nextLine().toLowerCase()) {
                default:
                case "n":
                    newRopChain();
                    break;
                case "o": openRopChain();
                    break;
                case "d": deleteRopChain();
                    break;
                case "p": printPayload();
                    break;
                case "r": resetPayload();
                    break;
                case "q": return;
            }
        }
    }

    private void displayPayloadMenu() {
        System.out.println(payload.getName());

        for (ExploitObject ropChain : payload.getList()) {
            System.out.printf("\t%s\n", ((RopChain)ropChain).getName());
        }

        System.out.print("[N]ew ROPchain  ");
        System.out.print("[o]pen ROPchain  ");
        System.out.print("[d]elete ROPchain  ");
        System.out.print("[p]rint ROPchain  ");
        System.out.print("[r]eset  ");
        System.out.println("[q]uit");
    }

    private void newRopChain() {
        RopChain ropChain = new RopChain();
        int index = 0;

        System.out.printf("index (default %d): ", index);
        String input;
        input = scanner.nextLine();

        if (!input.isEmpty()) {
            index = Integer.parseInt(input);
        }

        payload.add(ropChain, index);

        System.out.printf("ROPChain name (default %s): ", ROPCHAIN_DEFAULT_NAME);
        input = scanner.nextLine();

        if (!input.isEmpty()) {
            ropChain.setName(input);
        } else {
            ropChain.setName(ROPCHAIN_DEFAULT_NAME);
        }
    }

    private void openRopChain() {
        int index = 0;
        RopChain ropChain = (RopChain) payload.get(index);
        // TODO
    }

    private void deleteRopChain() {
        int index = 0;

        payload.remove(index);
    }

    private void printPayload() {
        System.out.println(payload.getScript());
    }

    private void resetPayload() {
        payload = new Payload();

        System.out.printf("Payload name (default %s): ", PAYLOAD_DEFAULT_NAME);
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            payload.setName(input);
        } else {
            payload.setName(PAYLOAD_DEFAULT_NAME);
        }
    }
}
