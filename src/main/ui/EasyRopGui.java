package ui;

import javax.swing.*;

// EasyRop Application with graphical user interface
public class EasyRopGui {
    private JFrame frame;

    // EFFECTS: Creates a new EasyRop GUI application and launches the GUI
    public EasyRopGui() {
        frame = new JFrame("Easy Rop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        run();
    }

    // MODIFIES: this.frame
    // EFFECTS: Makes the frame visible
    private void run() {
        frame.setVisible(true);
    }

    // EFFECTS: Starts the graphical user interface
    public static void main(String[] args) {
        new EasyRopGui();
    }
}
