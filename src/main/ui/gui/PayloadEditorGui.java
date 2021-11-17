package ui.gui;

import model.GadgetCollection;
import model.Payload;
import model.RopChain;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PayloadEditorGui extends CollectionEditorGui {
    private final Payload payload;
    private final JMenuItem insertRopChainItem;

    public PayloadEditorGui(Payload payload) {
        this.payload = payload;

        insertRopChainItem = new JMenuItem("New ROP Chain");
        insertRopChainItem.addActionListener(this);

        super.init();
    }

    @Override
    GadgetCollection getCollection() {
        return payload;
    }

    @Override
    void removeElement(String params) {
        int index = Integer.parseInt(params);
        payload.remove(index);
    }

    @Override
    void saveCollection() {
        if (showSaveDialog()) {
            try {
                new JsonWriter(getSelectedFile()).writeObject(payload);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not save payload to file " + e.getMessage(),
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
                new JsonReader(getSelectedFile()).payloadFromFile(payload);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not load payload from file " + e.getMessage(),
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
                    "New payload name:\n",
                    "Rename Payload",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    payload.getName()
            );

            if (name == null) {
                return;
            }
        } while (name.length() == 0);

        payload.setName(name);
        setTitleLabel(payload.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(insertRopChainItem)) {
            insertRopChain();
        } else {
            super.actionPerformed(e);
        }
    }

    @Override
    void addInsertOptions(JMenu insertMenu) {
        insertMenu.add(insertRopChainItem);
    }

    private void insertRopChain() {
        String name;

        do {
            name = (String) JOptionPane.showInputDialog(
                    null,
                    "New ROP chain name:\n",
                    "New ROP Chain",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "ropChain"
            );

            if (name == null) {
                return;
            }
        } while (name.length() == 0);

        RopChain ropChain = new RopChain();
        ropChain.setName(name);

        payload.add(ropChain, payload.getLength());
        insert(ropChain);
    }
}
