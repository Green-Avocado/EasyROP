package ui.gui;

import model.GadgetCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CollectionEditorGui extends JFrame implements ActionListener {
    private JPanel collectionViewer;

    public CollectionEditorGui() {
        super("Easy Rop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        setContentPane(content);
    }

    void init() {
        JLabel title = new JLabel(getCollection().getName());
        getContentPane().add(title);

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

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem loadMenu = new JMenuItem("Open");
        menu.add(loadMenu);

        JMenuItem saveMenu = new JMenuItem("Save as");
        menu.add(saveMenu);
    }
}
