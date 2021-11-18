package ui.gui.dialogs;

import javafx.util.Pair;
import model.ExploitObject;
import ui.gui.editors.RopChainEditorGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;

// Represents a dialog where the user can enter parameters for a new InstructionsGadget.
public class NewInstructionsGadgetDialog extends NewExploitElementDialog {
    private final JButton addFieldButton = new JButton("+");

    // EFFECTS: Creates a new NewInstructionsGadgetDialog with the given parentFrame and exploitObject.
    //          Sets the title and initial JTextField values for the super class.
    //          Adds a button for creating new fields to add more instructions.
    public NewInstructionsGadgetDialog(RopChainEditorGui parentFrame, ExploitObject exploitObject) {
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

    // MODIFIES: this.getFieldsPanel(), super.fieldInputs()
    // EFFECTS: Handles ActionEvents for this dialog.
    //          If the addFieldButton was pressed, adds a new input field to the dialog and list of input fields.
    //          Otherwise, passes the ActionEvent to the super.actionPerformed().
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
