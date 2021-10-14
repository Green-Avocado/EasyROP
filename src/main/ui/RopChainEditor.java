package ui;

import model.RopChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RopChainEditor extends CollectionEditor {

    public RopChainEditor(RopChain ropChain, ConsoleContext parentContext) {
        super(ropChain, parentContext);
    }

    ConsoleContext defaultAction() {
        add();
        return this;
    }

    ConsoleContext add() {
        // TODO
        return this;
    }

    ConsoleContext open() {
        // TODO
        return this;
    }

    ConsoleContext delete() {
        // TODO
        return this;
    }

    ConsoleContext reset() {
        // TODO
        return this;
    }

    ConsoleContext print() {
        // TODO
        return this;
    }

    List<String> getMenu() {
        ArrayList<String> menu = new ArrayList<>(Arrays.asList(
                "[N]ew gadget",
                "[o]pen gadget",
                "[d]elete gadget",
                "[p]rint gadget"
        ));

        menu.addAll(super.getMenu());

        return menu;
    }
}
