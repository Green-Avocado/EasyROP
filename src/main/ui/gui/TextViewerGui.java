package ui.gui;

import javax.swing.*;

public class TextViewerGui extends JFrame {
    private final String content;

    public TextViewerGui(String title, String content) {
        super(title);
        this.content = content;

        JTextArea contentLabel = new JTextArea(content);
        contentLabel.setEditable(false);
        add(contentLabel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
