package ui;

import model.Payload;
import ui.gui.PayloadEditorGui;
import ui.gui.RopChainEditorGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// EasyRop Application with graphical user interface
public class EasyRopGui {
    private JFrame frame;

    // EFFECTS: Creates a new EasyRop GUI application and launches the GUI
    public EasyRopGui() {
        frame = new JFrame("Easy Rop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        PayloadEditorGui payloadEditorGui = new PayloadEditorGui(new Payload());

        run();
    }

    // MODIFIES: this.frame
    // EFFECTS: Prompts the user to create the initial payload by entering a payload name
    private void run() {
        JDialog payloadNameDialogue = new JDialog(frame, "New Payload");
        payloadNameDialogue.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        JPanel panel = new JPanel();
        payloadNameDialogue.setContentPane(panel);

        JLabel label = new JLabel("New payload name:");
        panel.add(label);

        JTextField textField = new JTextField(10);
        panel.add(textField);

        JButton buttonOk = new JButton();
        panel.add(buttonOk);

        JButton buttonCancel = new JButton();
        panel.add(buttonCancel);

        payloadNameDialogue.pack();
        payloadNameDialogue.setLocationRelativeTo(null);
        payloadNameDialogue.setVisible(true);
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
