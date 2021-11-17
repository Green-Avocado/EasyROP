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
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem viewScriptMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem renameMenuItem;

    public CollectionEditorGui() {
        super("Easy Rop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);

        fileChooser = new JFileChooser("./data/");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JavaScript Object Notation data files", "json"));

        titleLabel = new JLabel();
        titleLabel.setMinimumSize(new Dimension(50, 25));
        getContentPane().add(titleLabel);

        collectionViewer = new JPanel();
        collectionViewer.setLayout(new BoxLayout(collectionViewer, BoxLayout.Y_AXIS));
        getContentPane().add(collectionViewer);
    }

    void init() {
        titleLabel.setText(getCollection().getName());

        initMenuBar();

        pack();
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

    void insert(ExploitObject exploitObject) {
        JPanel objectPanel = new JPanel();

        JLabel objectLabel = new JLabel(exploitObject.getName());
        objectPanel.add(objectLabel);

        collectionViewer.add(objectPanel);
        pack();
    }

    abstract GadgetCollection getCollection();

    // REQUIRES: params is a valid index with an object that can be removed from the collection.
    // MODIFIES: this
    // EFFECTS: Removes the element at the specified index from the collection.
    abstract void removeElement(String params);

    abstract void saveCollection();

    abstract void loadCollection();

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

        loadMenuItem = new JMenuItem("Open");
        loadMenuItem.addActionListener(this);
        fileMenu.add(loadMenuItem);

        saveMenuItem = new JMenuItem("Save as");
        saveMenuItem.addActionListener(this);
        fileMenu.add(saveMenuItem);

        viewScriptMenuItem = new JMenuItem("View script");
        viewScriptMenuItem.addActionListener(this);
        fileMenu.add(viewScriptMenuItem);

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);
    }

    private void addEditMenu(JMenuBar menuBar) {
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        renameMenuItem = new JMenuItem("Rename");
        renameMenuItem.addActionListener(this);
        editMenu.add(renameMenuItem);

        JMenu insertMenu = new JMenu("Insert");
        editMenu.add(insertMenu);
        addInsertOptions(insertMenu);
    }
}
