package ui.gui;

import javax.swing.*;
import java.awt.*;

// Represents a frame where the script output of a collection can be displayed.
public class TextViewerGui extends JFrame {

    // EFFECTS: Creates a new TextViewerGui with the given title and script content, then makes the frame visible.
    public TextViewerGui(String title, String content) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea contentLabel = new JTextArea(content);
        contentLabel.setEditable(false);
        add(contentLabel);

        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
