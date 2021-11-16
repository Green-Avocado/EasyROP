package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CollectionEditorGui extends JPanel implements ActionListener {

    public CollectionEditorGui() {
    }

    abstract void addElement(String params);

    // REQUIRES: params is a valid index with an object that can be removed from the collection.
    // MODIFIES: this
    // EFFECTS: Removes the element at the specified index from the collection.
    abstract void removeElement(String params);

    // REQUIRES: e.getActionCommand() is one of the specified actions,
    //           e.paramString() is a valid parameter string for the specified action command.
    // EFFECTS: calls the handler method for the given action command.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addElement(e.paramString());
                break;
            case "remove":
                removeElement(e.paramString());
                break;
        }
    }
}
