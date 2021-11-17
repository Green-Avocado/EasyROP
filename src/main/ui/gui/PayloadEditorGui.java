package ui.gui;

import model.GadgetCollection;
import model.Payload;

import javax.swing.*;

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
    void addInsertOptions(JMenu insertMenu) {
        JMenuItem insertPayloadItem = new JMenuItem("New Payload");
        insertMenu.add(insertPayloadItem);
    }
}
