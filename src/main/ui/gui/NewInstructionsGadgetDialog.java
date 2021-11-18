package ui.gui;

import javafx.util.Pair;
import model.ExploitObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;

public class NewInstructionsGadgetDialog extends NewExploitElementDialog {
    private final JButton addFieldButton = new JButton("+");

    NewInstructionsGadgetDialog(RopChainEditorGui parentFrame, ExploitObject exploitObject) {
        super(
                parentFrame,
                exploitObject,
                "New Instructions Gadget",
                Collections.singletonList(new Pair<>("Base", "exe"))
        );

        addFieldButton.addActionListener(this);
        getFieldsPanel().add(addFieldButton);

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(addFieldButton)) {
            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
            fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel fieldLabel = new JLabel("Instruction: ");
            fieldPanel.add(fieldLabel);

            fieldPanel.add(Box.createHorizontalGlue());

            JTextField fieldInput = new JTextField();
            fieldPanel.add(fieldInput);
            addFieldInput(fieldInput);

            getFieldsPanel().add(fieldPanel, getFieldsPanel().getComponents().length - 1);

            pack();
        } else {
            super.actionPerformed(e);
        }
    }
}
