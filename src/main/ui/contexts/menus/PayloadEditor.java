package ui.contexts.menus;

import model.Payload;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.collections.NewRopChainContext;
import ui.contexts.prompts.collections.OpenRopChainContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayloadEditor extends CollectionEditor {

    public PayloadEditor(Payload payload) {
        super(payload, null);
    }

    List<String> getMenu() {
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(
                "[N]ew ROPchain",
                "[o]pen ROPchain",
                "[m]move ROPchain",
                "[d]elete ROPchain",
                "[p]rint payload"
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
}
