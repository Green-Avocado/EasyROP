package ui.gui.listItems;

import model.RopChain;
import ui.gui.editors.PayloadEditorGui;
import ui.gui.editors.RopChainEditorGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a single RopChain display in a PayloadEditorGui list of RopChains.
public class RopChainListItem extends JPanel implements ActionListener {
    private final PayloadEditorGui parentFrame;
    private final RopChain ropChain;
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");

    // EFFECTS: Creates a new RopChainListItem for the given parentFrame, representing the given ropChain.
    public RopChainListItem(PayloadEditorGui parentFrame, RopChain ropChain) {
        this.parentFrame = parentFrame;
        this.ropChain = ropChain;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(ropChain.getName()));
        add(Box.createHorizontalGlue());

        editButton.addActionListener(this);
        deleteButton.addActionListener(this);

        add(editButton);
        add(deleteButton);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // MODIFIES: this.parentFrame.getCollection(), this
    // EFFECTS: Handles edit and delete button ActionEvents.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(editButton)) {
            edit();
        } else if (source.equals(deleteButton)) {
            delete();
        }
    }

    // EFFECTS: Launches a RopChainEditorGui to edit this.ropChain.
    private void edit() {
        new RopChainEditorGui(parentFrame, ropChain);
    }

    // MODIFIES: this.parentFrame.getCollection(), this
    // EFFECTS: Removes the ropChain this represents from the payload, and removes this list item from the GUI.
    private void delete() {
        int index = parentFrame.getCollection().getList().indexOf(ropChain);
        parentFrame.getCollection().remove(index);
        parentFrame.reload();
    }
}
