package ui.gui;

import model.GadgetCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CollectionEditorGui extends JFrame implements ActionListener {
    private JPanel collectionViewer;
    private JLabel titleLabel;

    public CollectionEditorGui() {
        super("Easy Rop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        setContentPane(content);
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

    abstract GadgetCollection getCollection();

    abstract void addElement(String params);

    // REQUIRES: params is a valid index with an object that can be removed from the collection.
    // MODIFIES: this
    // EFFECTS: Removes the element at the specified index from the collection.
    abstract void removeElement(String params);

    abstract void saveCollection(String params);

    abstract void loadCollection(String params);

    // REQUIRES: e.getActionCommand() is one of the specified actions,
    //           e.paramString() is a valid parameter string for the specified action command.
    // EFFECTS: calls the handler method for the given action command.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addElement(e.paramString());
                break;
            case "remove":
                removeElement(e.paramString());
                break;
            case "save":
                saveCollection(e.paramString());
                break;
            case "load":
                loadCollection(e.paramString());
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and sets the menu bar with menu items
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem loadMenuItem = new JMenuItem("Open");
        fileMenu.add(loadMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Save as");
        fileMenu.add(saveMenuItem);

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        JMenuItem renameMenuItem = new JMenuItem("Rename");
        editMenu.add(renameMenuItem);
        JMenuItem insertMenuItem = new JMenuItem("Insert");
        editMenu.add(insertMenuItem);
    }
}
