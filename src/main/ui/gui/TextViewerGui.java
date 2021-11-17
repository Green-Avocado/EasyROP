package ui.gui;

import javax.swing.*;

public class TextViewerGui extends JFrame {
    private final String content;

    public TextViewerGui(String title, String content) {
        super(title);
        this.content = content;

        JLabel contentLabel = new JLabel(content);
        add(contentLabel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
