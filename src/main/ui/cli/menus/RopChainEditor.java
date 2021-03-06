package ui.cli.menus;

import model.RopChain;
import ui.cli.ConsoleContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a UI context that edits RopChain objects
public class RopChainEditor extends CollectionEditor {

    public RopChainEditor(ConsoleContext parentContext, RopChain ropChain) {
        super(parentContext, ropChain);
    }

    // EFFECTS: Appends RopChainEditor-specific menu options with CollectionEditor options.
    //          Returns the list of menu options.
    @Override
    List<String> getMenu() {
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(
                "[N]ew gadget",
                "[m]ove gadget",
                "[d]elete gadget",
                "[p]rint ROPchain"
        ));

        menu.addAll(super.getMenu());

        return menu;
    }

    // EFFECTS: Returns a context for adding a new gadget or padding to this collection.
    @Override
    ConsoleContext add() {
        return new GadgetCreator(this);
    }

    // EFFECTS: Returns this context.
    @Override
    ConsoleContext open() {
        return this; // do nothing
    }
}
