package ui.contexts.menus;

import model.Payload;
import model.RopChain;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.collections.RopChainSelector;
import ui.contexts.prompts.util.ExploitObjectCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a UI context that edits Payload objects
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
        RopChain ropChain = new RopChain();
        ArrayList<List<String>> promptData = new ArrayList<>();

        promptData.add(Arrays.asList("New ROPchain name", "ropChain"));

        return new ExploitObjectCreator(
                this,
                ropChain,
                promptData
        );
    }

    ConsoleContext open() {
        if (getCollection().getLength() > 0) {
            return new RopChainSelector(this);
        } else {
            return this;
        }
    }
}
