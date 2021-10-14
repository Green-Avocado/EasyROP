package ui;

import model.ExploitObject;
import model.Payload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayloadEditor extends CollectionEditor {

    public PayloadEditor(Payload payload) {
        super(payload, null);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "n":
                return add();
            case "o":
                open();
                return this;
            case "d":
                delete();
                return this;
            case "p":
                print();
                return this;
            default:
                return super.handleInput(input);
        }
    }

    ConsoleContext defaultAction() {
        return add();
    }

    public String getContextString() {
        StringBuilder contextString = new StringBuilder();

        contextString.append(getCollection().getName());
        contextString.append(":\n");

        for (ExploitObject exploitObject : getCollection().getList()) {
            contextString.append("  ");
            contextString.append(exploitObject.getName());
            contextString.append("\n");
        }

        contextString.append(super.getContextString());

        return contextString.toString();
    }

    List<String> getMenu() {
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(
                "[N]ew ROPchain",
                "[o]pen ROPchain",
                "[d]elete ROPchain",
                "[p]rint ROPchain"
        ));

        menu.addAll(super.getMenu());

        return menu;
    }

    ConsoleContext add() {
        return new NewRopChainContext(this);
    }

    void open() {
        // TODO
    }

    void delete() {
        // TODO
    }

    void reset() {
        // TODO
    }

    void print() {
        // TODO
    }
}
