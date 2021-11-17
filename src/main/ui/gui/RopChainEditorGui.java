package ui.gui;

import model.GadgetCollection;
import model.RopChain;

import javax.swing.*;

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
    void saveCollection() {
    }

    @Override
    void loadCollection() {
    }

    @Override
    void renameCollection() {
        String name;

        do {
            name = (String) JOptionPane.showInputDialog(
                    null,
                    "New ROP chain name:\n",
                    "Rename ROP Chain",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    ropChain.getName()
            );

            if (name == null) {
                return;
            }
        } while (name.length() == 0);

        ropChain.setName(name);
        setTitleLabel(ropChain.getName());
    }

    @Override
    void addInsertOptions(JMenu insertMenu) {
        JMenuItem insertPaddingItem = new JMenuItem("New Padding");
        insertMenu.add(insertPaddingItem);

        JMenuItem insertAddressItem = new JMenuItem("New Address Gadget");
        insertMenu.add(insertAddressItem);

        JMenuItem insertInstructionsItem = new JMenuItem("New Instructions Gadget");
        insertMenu.add(insertInstructionsItem);

        JMenuItem insertStringItem = new JMenuItem("New String Gadget");
        insertMenu.add(insertStringItem);

        JMenuItem insertSymbolItem = new JMenuItem("New Symbol Gadget");
        insertMenu.add(insertSymbolItem);
    }
}
