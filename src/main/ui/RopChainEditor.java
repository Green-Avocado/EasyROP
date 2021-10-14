package ui;

import model.ExploitObject;
import model.GadgetCollection;
import model.RopChain;

import java.util.Arrays;
import java.util.List;

public class RopChainEditor extends CollectionEditor {

    public RopChainEditor(RopChain ropChain, ConsoleContext parentContext) {
        super(ropChain, parentContext);
    }

    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "n":
                add();
                return this;
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
        add();
        return this;
    }

    void add() {
        // TODO
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

    List<String> getMenu() {
        List<String> menu = Arrays.asList(
                "[N]ew gadget",
                "[o]pen gadget",
                "[d]elete gadget",
                "[p]rint gadget"
        );

        menu.addAll(super.getMenu());

        return menu;
    }
}
