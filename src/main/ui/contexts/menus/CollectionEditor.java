package ui.contexts.menus;

import model.GadgetCollection;
import model.gadgets.ExploitObject;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.collections.ScriptViewer;
import ui.contexts.prompts.collections.SetCollectionName;
import ui.contexts.prompts.util.ExploitObjectRemover;
import ui.contexts.prompts.util.ExploitObjectMover;

import java.util.Arrays;
import java.util.List;

public abstract class CollectionEditor extends MenuContext {
    private final GadgetCollection collection;

    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext) {
        super(parentContext);

        this.collection = collection;
    }

    public String getContextString() {
        StringBuilder contextString = new StringBuilder();

        contextString.append(getCollection().getName());
        contextString.append(":\n");

        for (ExploitObject exploitObject : getCollection().getList()) {
            contextString.append("  ");
            contextString.append(exploitObject.getName());
            contextString.append("\n");
        }

        contextString.append(super.getContextString());

        return contextString.toString();
    }


    public ConsoleContext handleInput(String input) {
        input = input.toLowerCase();

        switch (input) {
            case "":
            case "n":
                return add();
            case "o":
                return open();
            case "m":
                return move();
            case "d":
                return delete();
            case "p":
                return print();
            case "e":
                return editName();
            case "q":
                return getParentContext();
            default:
                return this;
        }
    }

    public GadgetCollection getCollection() {
        return collection;
    }

    List<String> getMenu() {
        return Arrays.asList("[e]dit name", "[q]uit");
    }

    ConsoleContext delete() {
        if (getCollection().getLength() > 0) {
            return new ExploitObjectRemover(this);
        } else {
            return this;
        }
    }

    ConsoleContext move() {
        if (getCollection().getLength() > 1) {
            return new ExploitObjectMover(this);
        } else {
            return this;
        }
    }

    ConsoleContext editName() {
        return new SetCollectionName(this);
    }

    ConsoleContext print() {
        return new ScriptViewer(this, getCollection().getScript());
    }

    abstract ConsoleContext add();

    abstract ConsoleContext open();
}
