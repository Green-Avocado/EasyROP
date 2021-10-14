package ui;

import model.Payload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayloadEditor extends CollectionEditor {

    public PayloadEditor(Payload payload) {
        super(payload, null);
    }

    ConsoleContext defaultAction() {
        return add();
    }

    List<String> getMenu() {
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(
                "[N]ew ROPchain",
                "[o]pen ROPchain",
                "[m]move ROPchain",
                "[d]elete ROPchain",
                "[p]rint ROPchain"
        ));

        menu.addAll(super.getMenu());

        return menu;
    }

    ConsoleContext add() {
        return new NewRopChainContext(this);
    }

    ConsoleContext open() {
        if (getCollection().getLength() > 0) {
            return new OpenRopChainContext(this);
        } else {
            return this;
        }
    }

    ConsoleContext move() {
        if (getCollection().getLength() > 1) {
            return new MoveExploitObjectContext(this);
        } else {
            return this;
        }
    }

    ConsoleContext delete() {
        if (getCollection().getLength() > 0) {
            return new DeleteRopChainContext(this);
        } else {
            return this;
        }
    }

    ConsoleContext reset() {
        return new NewPayloadContext();
    }

    ConsoleContext print() {
        return new PrintScriptContext(this, getCollection().getScript());
    }
}
