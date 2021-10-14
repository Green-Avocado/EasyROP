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

    ConsoleContext defaultAction() {
        return add();
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
        return new NewPayloadContext();
    }

    ConsoleContext print() {
        return new PrintScriptContext(this, getCollection().getScript());
    }
}
