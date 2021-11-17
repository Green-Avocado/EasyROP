package ui.gui;

import model.GadgetCollection;
import model.RopChain;

public class RopChainEditorGui extends CollectionEditorGui {
    private final RopChain ropChain;

    public RopChainEditorGui(RopChain ropChain) {
        this.ropChain = ropChain;
    }

    @Override
    GadgetCollection getCollection() {
        return ropChain;
    }

    @Override
    void addElement(String params) {
        int index = Integer.parseInt(params);
    }

    @Override
    void removeElement(String params) {
        int index = Integer.parseInt(params);
        ropChain.remove(index);
    }

    @Override
    void saveCollection(String params) {
    }

    @Override
    void loadCollection(String params) {
    }
}
