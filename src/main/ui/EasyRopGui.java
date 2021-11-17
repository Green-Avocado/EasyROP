package ui;

import model.Payload;
import ui.gui.PayloadEditorGui;

import javax.swing.*;

// EasyRop Application with graphical user interface
public class EasyRopGui {

    // EFFECTS: Creates a new EasyRop GUI application and launches the GUI
    public EasyRopGui() {
        run();
    }

    // MODIFIES: this.frame
    // EFFECTS: Prompts the user to create the initial payload by entering a payload name.
    //          If empty response, prompt again.
    //          If cancelled, close the GUI.
    //          If ok and non-empty response, set payload name and show GUI.
    private void run() {
        String payloadName;
        do {
            payloadName = (String) JOptionPane.showInputDialog(
                    null,
                    "New payload name:\n",
                    "New Payload",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "payload"
            );

            if (payloadName == null) {
                return;
            }
        } while (payloadName.length() == 0);

        Payload payload = new Payload();
        payload.setName(payloadName);

        new PayloadEditorGui(payload);
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
