package ui.gui;

import model.GadgetCollection;
import model.Payload;

public class PayloadEditorGui extends CollectionEditorGui {
    private final Payload payload;

    public PayloadEditorGui(Payload payload) {
        this.payload = payload;

        super.init();
    }

    @Override
    GadgetCollection getCollection() {
        return payload;
    }

    @Override
    void addElement(String params) {
        int index = Integer.parseInt(params);
    }

    @Override
    void removeElement(String params) {
        int index = Integer.parseInt(params);
        payload.remove(index);
    }

    @Override
    void saveCollection(String params) {
    }

    @Override
    void loadCollection(String params) {
    }
}
