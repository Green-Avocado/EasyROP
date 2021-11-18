package ui.gui.editors;

import model.ExploitObject;
import model.GadgetCollection;
import model.Payload;
import model.RopChain;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.listitems.RopChainListItem;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represents a window where a user can edit a Payload.
public class PayloadEditorGui extends CollectionEditorGui {
    private final Payload payload;
    private final JMenuItem insertRopChainItem = new JMenuItem("New ROP Chain");

    // EFFECTS: Creates a new PayloadEditorGui with a given payload.
    public PayloadEditorGui(Payload payload) {
        this.payload = payload;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.init();
    }

    // EFFECTS: Returns the payload being edited by this frame.
    @Override
    public GadgetCollection getCollection() {
        return payload;
    }

    @Override
    public void insert(ExploitObject exploitObject) {
        getCollectionViewer().add(new RopChainListItem(this, (RopChain) exploitObject));
        validate();
    }

    // EFFECTS: Saves this.payload to the specified file.
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

    // MODIFIES: this.payload, this
    // EFFECTS: Overwrites this.payload with data from the specified file.
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

        reload();
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
            newRopChain();
        } else {
            super.actionPerformed(e);
        }
    }

    @Override
    void addInsertOptions(JMenu insertMenu) {
        insertRopChainItem.addActionListener(this);
        insertMenu.add(insertRopChainItem);
    }

    private void newRopChain() {
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
