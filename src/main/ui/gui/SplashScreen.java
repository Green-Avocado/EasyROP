package ui.gui;

import model.Payload;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the initial window for the GUI application, with a splash image and options to start the program.
public class SplashScreen extends JFrame implements ActionListener {
    private final JButton newPayloadButton = new JButton("Create New Payload");
    private final JButton fromFileButton = new JButton("Load Existing Payload");

    // EFFECTS: Creates a new SplashScreen and initialises all components.
    public SplashScreen() {
        super("Easy ROP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initContentPane();
        initSplash();
        initButtons();

        getContentPane().setBackground(Color.WHITE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Starts the main application from a new Payload or from a saved Payload file,
    //          depending on the ActionEvent.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(newPayloadButton)) {
            startFromNewPayload();
        } else if (source.equals(fromFileButton)) {
            startFromFile();
        }
    }

    // MODIFIES: this
    // EFFECTS: Prompts the user for the name of the new Payload.
    //          If a valid name is entered, disposes of this frame and
    //          starts the main application with a new Payload using the given name
    private void startFromNewPayload() {
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

        dispose();

        new PayloadEditorGui(payload);
    }

    // MODIFIES: this
    // EFFECTS: Prompts the user to select a Payload file.
    //          If a valid file is given, disposes of this frame and
    //          starts the main application using the Payload from the given file.
    private void startFromFile() {
        JFileChooser fileChooser = new JFileChooser("./data/");
        Payload payload = new Payload();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                new JsonReader(fileChooser.getSelectedFile().getAbsolutePath()).payloadFromFile(payload);

                dispose();

                new PayloadEditorGui(payload);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not load payload from file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and sets the main panel for this frame.
    private void initContentPane() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        setContentPane(content);
    }

    // MODIFIES: this
    // EFFECTS: Creates the splash image for this frame and adds it to the content pane.
    private void initSplash() {
        ImageIcon splash = new ImageIcon("./data/splash.png");
        JLabel splashLabel = new JLabel(splash);
        splashLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(splashLabel);
    }

    private void initButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsPanel.setBackground(Color.WHITE);
        getContentPane().add(buttonsPanel);

        newPayloadButton.addActionListener(this);
        fromFileButton.addActionListener(this);

        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(newPayloadButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(fromFileButton);
        buttonsPanel.add(Box.createHorizontalGlue());
    }
}
