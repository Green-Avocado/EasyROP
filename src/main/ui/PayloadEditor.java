package ui;

import model.Payload;
import model.RopChain;

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
}
