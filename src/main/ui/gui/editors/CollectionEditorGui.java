package ui.gui.editors;

import model.ExploitObject;
import model.GadgetCollection;
import ui.gui.TextViewerGui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// Represents a window where a user can edit a Payload or RopChain.
public abstract class CollectionEditorGui extends JFrame implements ActionListener {
    private final JPanel collectionViewer;
    private final JLabel titleLabel;
    private final JMenuItem loadMenuItem = new JMenuItem("Open");
    private final JMenuItem saveMenuItem = new JMenuItem("Save as");
    private final JMenuItem viewScriptMenuItem = new JMenuItem("View script");
    private final JMenuItem exitMenuItem = new JMenuItem("Exit");
    private final JMenuItem renameMenuItem = new JMenuItem("Rename");
    JFileChooser fileChooser;

    // EFFECTS: Creates a new CollectionEditorGui.
    public CollectionEditorGui() {
        super("Easy Rop");

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        setContentPane(content);

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

    // MODIFIES: this
    // EFFECTS: Initialises final components of this frame after the concrete type has been constructed.
    //          Sets the frame to be visible.
    void init() {
        titleLabel.setText(getCollection().getName());

        initMenuBar();

        reload();

        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this.titleLabel
    // EFFECTS: Changes the text of the title label to the given title.
    void setTitleLabel(String title) {
        titleLabel.setText(title);
    }

    // MODIFIES: this.fileChooser
    // EFFECTS: Prompt the user to pick a file to save to.
    //          Returns true if successful or false otherwise.
    boolean showSaveDialog() {
        return fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION;
    }

    // MODIFIES: this.fileChooser
    // EFFECTS: Prompt the user to pick a file to open.
    //          Returns true if successful or false otherwise.
    boolean showOpenDialog() {
        return fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION;
    }

    // EFFECTS: Returns the absolute path to the selected file.
    String getSelectedFile() {
        return fileChooser.getSelectedFile().getAbsolutePath();
    }

    // EFFECTS: Returns a reference to the collectionViewer panel.
    public JPanel getCollectionViewer() {
        return collectionViewer;
    }

    // MODIFIES: this
    // EFFECTS: Refreshes all mutable GUI elements.
    public void reload() {
        collectionViewer.removeAll();

        setTitleLabel(getCollection().getName());

        for (ExploitObject exploitObject : getCollection().getList()) {
            insert(exploitObject);
        }

        validate();
    }

    // EFFECTS: Returns a reference to the GadgetCollection being edited by this frame.
    public abstract GadgetCollection getCollection();

    // MODIFIES: this.collectionViewer
    // EFFECTS: Inserts the given exploitObject as a list item into the collectionViewer.
    public abstract void insert(ExploitObject exploitObject);

    // EFFECTS: Save the current collection to a specified file.
    abstract void saveCollection();

    // MODIFIES: this.getCollection, this
    // EFFECTS: Overwrites the current collection with data from the specified file.
    abstract void loadCollection();

    // MODIFIES: this.getCollection, this
    // EFFECTS: Prompts the user for a new name.
    //          If successful, renames the collection to the specified string.
    abstract void renameCollection();

    // MODIFIES: this
    // EFFECTS: Adds insert options for the insert menu specific to the concrete type of this frame.
    abstract void addInsertOptions(JMenu insertMenu);

    // MODIFIES: this.getCollection, this
    // EFFECTS: Calls the handler method for the given action command.
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

    // MODIFIES: this
    // EFFECTS: Creates the file menu and its components.
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

    // MODIFIES: this
    // EFFECTS: Creates the edit menu and its components.
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
