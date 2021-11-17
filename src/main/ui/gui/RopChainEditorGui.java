package ui.gui;

import model.ExploitObject;
import model.GadgetCollection;
import model.RopChain;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a window where a user can edit a RopChain.
public class RopChainEditorGui extends CollectionEditorGui {
    private final RopChain ropChain;
    private final JMenuItem insertPaddingItem = new JMenuItem("New Padding");
    private final JMenuItem insertAddressItem = new JMenuItem("New Address Gadget");
    private final JMenuItem insertInstructionsItem = new JMenuItem("New Instructions Gadget");
    private final JMenuItem insertStringItem = new JMenuItem("New String Gadget");
    private final JMenuItem insertSymbolItem = new JMenuItem("New Symbol Gadget");

    // EFFECTS: Creates a new RopChainEditorGui.
    public RopChainEditorGui(RopChain ropChain) {
        this.ropChain = ropChain;

        super.init();
    }

    @Override
    GadgetCollection getCollection() {
        return ropChain;
    }

    @Override
    void removeElement(String params) {
        int index = Integer.parseInt(params);
        ropChain.remove(index);
    }

    @Override
    void saveCollection() {
        if (showSaveDialog()) {
            try {
                new JsonWriter(getSelectedFile()).writeObject(ropChain);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not save ROP chain to file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    @Override
    void loadCollection() {
        if (showOpenDialog()) {
            try {
                new JsonReader(getSelectedFile()).ropChainFromFile(ropChain);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not load ROP chain from file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        clear();

        for (ExploitObject exploitObject : ropChain.getList()) {
            insert(exploitObject);
        }
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
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(insertPaddingItem)) {
            return;
        } else if (source.equals(insertAddressItem)) {
            return;
        } else if (source.equals(insertInstructionsItem)) {
            return;
        } else if (source.equals(insertStringItem)) {
            return;
        } else if (source.equals(insertSymbolItem)) {
            return;
        } else {
            super.actionPerformed(e);
        }
    }

    @Override
    void addInsertOptions(JMenu insertMenu) {
        insertPaddingItem.addActionListener(this);
        insertAddressItem.addActionListener(this);
        insertInstructionsItem.addActionListener(this);
        insertStringItem.addActionListener(this);
        insertSymbolItem.addActionListener(this);

        insertMenu.add(insertPaddingItem);
        insertMenu.add(insertAddressItem);
        insertMenu.add(insertInstructionsItem);
        insertMenu.add(insertStringItem);
        insertMenu.add(insertSymbolItem);
    }
}
