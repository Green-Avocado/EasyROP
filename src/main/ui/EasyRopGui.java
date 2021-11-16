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
        String payloadName = JOptionPane.showInputDialog(
                frame,
                "New Payload Name:\n",
                "New Payload",
                JOptionPane.PLAIN_MESSAGE
        );

        if (payloadName == null || payloadName.length() == 0) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            return;
        }

        frame.setVisible(true);
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
