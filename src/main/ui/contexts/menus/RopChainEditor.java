package ui.contexts.menus;

import model.RopChain;
import ui.contexts.ConsoleContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RopChainEditor extends CollectionEditor {

    public RopChainEditor(RopChain ropChain, ConsoleContext parentContext) {
        super(ropChain, parentContext);
    }

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

    ConsoleContext add() {
        return new GadgetCreator(this);
    }

    ConsoleContext open() {
        return this; // do nothing
    }
}
