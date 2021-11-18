package ui;

import model.Payload;
import ui.gui.InitGui;
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
        new InitGui();
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
