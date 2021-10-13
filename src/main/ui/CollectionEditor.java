package ui;

import model.ExploitObject;
import model.GadgetCollection;

public abstract class CollectionEditor {
    private GadgetCollection collection;
    private ExploitObject storedObject;

    protected CollectionEditor(GadgetCollection collection) {
        this.collection = collection;
    }

    abstract void handleInput(String input);

    abstract void reset();

    abstract void setName();

    abstract String getContext();

    abstract String getMenu();
}
