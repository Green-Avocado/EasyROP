package ui.contexts.menus;

import model.GadgetCollection;
import model.gadgets.ExploitObject;
import ui.contexts.ConsoleContext;
import ui.contexts.prompts.ExploitObjectMover;
import ui.contexts.prompts.ExploitObjectRemover;
import ui.contexts.prompts.ScriptViewer;
import ui.contexts.prompts.SetCollectionName;

import java.util.Arrays;
import java.util.List;

// Represents a UI context that edits a GadgetCollection
public abstract class CollectionEditor extends MenuContext {
    private final GadgetCollection collection;

    // EFFECTS: Creates a new CollectionEditor context with a given collection and parentContext
    public CollectionEditor(GadgetCollection collection, ConsoleContext parentContext) {
        super(parentContext);

        this.collection = collection;
    }

    // EFFECTS: Returns the string to print at the start of this context
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

    // EFFECTS: Calls a helper function and returns its context depending on the menu option selected.
    //          If no valid option was selected, returns this context.
    public ConsoleContext handleInput(String input) {
        switch (input.toLowerCase()) {
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

    // EFFECTS: Returns the collection of this object.
    public GadgetCollection getCollection() {
        return collection;
    }

    // EFFECTS: Returns a list of menu options common to all implementations of this class
    List<String> getMenu() {
        return Arrays.asList("[e]dit name", "[q]uit");
    }

    // EFFECTS: If the collection is not empty, returns a context for removing an item from the collection,
    //          otherwise returns this context.
    ConsoleContext delete() {
        if (getCollection().getLength() > 0) {
            return new ExploitObjectRemover(this);
        } else {
            return this;
        }
    }

    // EFFECTS: If there are at least 2 elements in the collection, returns a context for moving items within
    //          a collection, otherwise returns this context.
    ConsoleContext move() {
        if (getCollection().getLength() > 1) {
            return new ExploitObjectMover(this);
        } else {
            return this;
        }
    }

    // EFFECTS: Returns a context for editing the collection name of this context.
    ConsoleContext editName() {
        return new SetCollectionName(this);
    }

    // EFFECTS: Returns a context for viewing the script of the collection.
    ConsoleContext print() {
        return new ScriptViewer(this, getCollection().getScript());
    }

    // EFFECTS: Returns a context for adding an item to the collection.
    abstract ConsoleContext add();

    // EFFECTS: Returns a context for selecting an item from the collection if supported by the concrete class.
    abstract ConsoleContext open();
}
