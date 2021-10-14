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
                return open();
            case "d":
                return delete();
            case "p":
                return print();
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
        return new NewRopChainNameContext(this);
    }

    ConsoleContext open() {
        if (getCollection().getLength() > 0) {
            return new OpenRopChainIndexContext(this);
        } else {
            return this;
        }
    }

    ConsoleContext delete() {
        if (getCollection().getLength() > 0) {
            return new DeleteRopChainIndexContext(this);
        } else {
            return this;
        }
    }

    ConsoleContext reset() {
        // TODO
        return this;
    }

    ConsoleContext print() {
        // TODO
        return this;
    }
}
