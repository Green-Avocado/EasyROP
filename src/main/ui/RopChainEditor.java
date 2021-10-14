package ui;

import model.RopChain;

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
                "[o]pen gadget",
                "[m]ove gadget",
                "[d]elete gadget",
                "[p]rint gadget"
        ));

        menu.addAll(super.getMenu());

        return menu;
    }

    ConsoleContext add() {
        // TODO
        return this;
    }

    ConsoleContext open() {
        // TODO
        return this;
    }

    ConsoleContext move() {
        if (getCollection().getLength() > 1) {
            return new MoveExploitObjectContext(this);
        } else {
            return this;
        }
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

}
