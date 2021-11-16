package ui;

import model.Payload;
import ui.gui.PayloadEditorGui;

import javax.swing.*;
import java.awt.event.WindowEvent;

// EasyRop Application with graphical user interface
public class EasyRopGui {
    private final JFrame frame;

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
    // EFFECTS: Prompts the user to create the initial payload by entering a payload name.
    //          If empty response, prompt again.
    //          If cancelled, close the GUI.
    //          If ok and non-empty response, set payload name and show GUI.
    private void run() {
        String payloadName = "";
        while (payloadName.length() == 0) {
            payloadName = (String) JOptionPane.showInputDialog(
                    frame,
                    "New Payload Name:\n",
                    "New Payload",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "payload"
            );

            if (payloadName == null) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                return;
            }
        }

        frame.setVisible(true);
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
