package ui.gui;

import model.ExploitObject;
import model.GadgetCollection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// Represents a window where a user can edit a Payload or RopChain.
public abstract class CollectionEditorGui extends JFrame implements ActionListener {
    private final JFileChooser fileChooser;
    private final JPanel collectionViewer;
    private final JLabel titleLabel;
    private final JMenuItem loadMenuItem = new JMenuItem("Open");
    private final JMenuItem saveMenuItem = new JMenuItem("Save as");
    private final JMenuItem viewScriptMenuItem = new JMenuItem("View script");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    private final JMenuItem renameMenuItem = new JMenuItem("Rename");

    public CollectionEditorGui() {
        super("Easy Rop");

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);

        fileChooser = new JFileChooser("./data/");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JavaScript Object Notation data files", "json"));

        getContentPane().add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        getContentPane().add(titlePanel);

        titleLabel = new JLabel();
        titlePanel.add(titleLabel);

        getContentPane().add(Box.createRigidArea(new Dimension(0, 8)));

        collectionViewer = new JPanel();
        collectionViewer.setLayout(new BoxLayout(collectionViewer, BoxLayout.Y_AXIS));
        getContentPane().add(collectionViewer);

        getContentPane().add(Box.createVerticalGlue());
    }

    void init() {
        titleLabel.setText(getCollection().getName());

        initMenuBar();

        reload();

        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void setTitleLabel(String title) {
        titleLabel.setText(title);
    }

    boolean showSaveDialog() {
        return fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION;
    }

    boolean showOpenDialog() {
        return fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION;
    }

    String getSelectedFile() {
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    public JPanel getCollectionViewer() {
        return collectionViewer;
    }

    void clear() {
        collectionViewer.removeAll();
    }

    void reload() {
        clear();

        for (ExploitObject exploitObject : getCollection().getList()) {
            insert(exploitObject);
        }

        validate();
    }

    abstract GadgetCollection getCollection();

    abstract void saveCollection();

    abstract void loadCollection();

    abstract void insert(ExploitObject exploitObject);

    abstract void renameCollection();

    abstract void addInsertOptions(JMenu insertMenu);

    // REQUIRES: e.getActionCommand() is one of the specified actions,
    //           e.paramString() is a valid parameter string for the specified action command.
    // EFFECTS: calls the handler method for the given action command.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(renameMenuItem)) {
            renameCollection();
        } else if (source.equals(saveMenuItem)) {
            saveCollection();
        } else if (source.equals(loadMenuItem)) {
            loadCollection();
        } else if (source.equals(viewScriptMenuItem)) {
            new TextViewerGui(getCollection().getName(), getCollection().getScript());
        } else if (source.equals(exitMenuItem)) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and sets the menu bar with menu items
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        addFileMenu(menuBar);
        addEditMenu(menuBar);
    }

    private void addFileMenu(JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        loadMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        viewScriptMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(viewScriptMenuItem);
        fileMenu.add(exitMenuItem);
    }

    private void addEditMenu(JMenuBar menuBar) {
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        renameMenuItem.addActionListener(this);
        editMenu.add(renameMenuItem);

        JMenu insertMenu = new JMenu("Insert");
        editMenu.add(insertMenu);
        addInsertOptions(insertMenu);
    }
}
