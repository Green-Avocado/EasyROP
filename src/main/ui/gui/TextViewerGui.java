package ui.gui;

import javax.swing.*;

// Represents a frame where the script output of a collection can be displayed.
public class TextViewerGui extends JFrame {

    // EFFECTS: Creates a new TextViewerGui with the given title and script content, then makes the frame visible.
    public TextViewerGui(String title, String content) {
        super(title);

        JTextArea contentLabel = new JTextArea(content);
        contentLabel.setEditable(false);
        add(contentLabel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
