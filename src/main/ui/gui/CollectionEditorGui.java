package ui.gui;

import model.GadgetCollection;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CollectionEditorGui extends JFrame implements ActionListener {
    private JPanel collectionViewer;
    private JLabel titleLabel;
    private JFileChooser fileChooser;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem renameMenuItem;

    public CollectionEditorGui() {
        super("Easy Rop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        setContentPane(content);

        fileChooser = new JFileChooser("./data/");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JavaScript Object Notation data files", "json"));
    }

    void init() {
        titleLabel = new JLabel(getCollection().getName());
        getContentPane().add(titleLabel);

        collectionViewer = new JPanel();
        getContentPane().add(collectionViewer);

        initMenuBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void setTitleLabel(String title) {
        titleLabel.setText(title);
    }

    JFileChooser getFileChooser() {
        return fileChooser;
    }

    abstract GadgetCollection getCollection();

    abstract void addElement(String params);

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
