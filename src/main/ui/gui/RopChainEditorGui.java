package ui.gui;

import model.GadgetCollection;
import model.RopChain;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RopChainEditorGui extends CollectionEditorGui {
    private final RopChain ropChain;
    private final JMenuItem insertPaddingItem;
    private final JMenuItem insertAddressItem;
    private final JMenuItem insertInstructionsItem;
    private final JMenuItem insertStringItem;
    private final JMenuItem insertSymbolItem;

    public RopChainEditorGui(RopChain ropChain) {
        this.ropChain = ropChain;

        insertPaddingItem = new JMenuItem("New Padding");
        insertPaddingItem.addActionListener(this);

        insertAddressItem = new JMenuItem("New Address Gadget");
        insertAddressItem.addActionListener(this);

        insertInstructionsItem = new JMenuItem("New Instructions Gadget");
        insertInstructionsItem.addActionListener(this);

        insertStringItem = new JMenuItem("New String Gadget");
        insertStringItem.addActionListener(this);

        insertSymbolItem = new JMenuItem("New Symbol Gadget");
        insertSymbolItem.addActionListener(this);

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
        JFileChooser fileChooser = getFileChooser();

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                new JsonWriter(fileChooser.getSelectedFile().getAbsolutePath()).writeObject(ropChain);
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
        JFileChooser fileChooser = getFileChooser();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                new JsonReader(fileChooser.getSelectedFile().getAbsolutePath()).ropChainFromFile(ropChain);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not load ROP chain from file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
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
        insertMenu.add(insertPaddingItem);
        insertMenu.add(insertAddressItem);
        insertMenu.add(insertInstructionsItem);
        insertMenu.add(insertStringItem);
        insertMenu.add(insertSymbolItem);
    }
}
