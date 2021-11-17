package ui.gui;

import model.RopChain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RopChainListItem extends JPanel implements ActionListener {
    private final PayloadEditorGui parentFrame;
    private final RopChain ropChain;
    private final JButton editButton;
    private final JButton deleteButton;

    public RopChainListItem(PayloadEditorGui parentFrame, RopChain ropChain) {
        this.parentFrame = parentFrame;
        this.ropChain = ropChain;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new JLabel(ropChain.getName()));
        add(Box.createHorizontalGlue());

        editButton = new JButton("Edit");
        editButton.addActionListener(this);
        add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        add(deleteButton);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(editButton)) {
            edit();
        } else if (source.equals(deleteButton)) {
            delete();
        }
    }

    private void edit() {
        new RopChainEditorGui(parentFrame, ropChain);
    }

    private void delete() {
        int index = parentFrame.getCollection().getList().indexOf(ropChain);
        parentFrame.getCollection().remove(index);
        parentFrame.reload();
    }
}
