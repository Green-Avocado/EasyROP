package ui.gui.editors;

import javafx.util.Pair;
import model.ExploitObject;
import model.GadgetCollection;
import model.RopChain;
import model.gadgets.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.listitems.ExploitObjectListItem;
import ui.gui.dialogs.NewExploitElementDialog;
import ui.gui.dialogs.NewInstructionsGadgetDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;

// Represents a window where a user can edit a RopChain.
public class RopChainEditorGui extends CollectionEditorGui {
    private final PayloadEditorGui parentFrame;
    private final RopChain ropChain;
    private final JMenuItem insertPaddingItem = new JMenuItem("New Padding");
    private final JMenuItem insertAddressItem = new JMenuItem("New Address Gadget");
    private final JMenuItem insertInstructionsItem = new JMenuItem("New Instructions Gadget");
    private final JMenuItem insertStringItem = new JMenuItem("New String Gadget");
    private final JMenuItem insertSymbolItem = new JMenuItem("New Symbol Gadget");

    // EFFECTS: Creates a new RopChainEditorGui with a given parentFrame and ropChain.
    public RopChainEditorGui(PayloadEditorGui parentFrame, RopChain ropChain) {
        this.parentFrame = parentFrame;
        this.ropChain = ropChain;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        super.init();
    }

    // MODIFIES: this.parentFrame, this
    // EFFECTS: Reloads all GUI elements for this frame and the parentFrame.
    @Override
    public void reload() {
        super.reload();
        parentFrame.reload();
    }

    // EFFECTS: Returns the ropChain this frame is editing.
    @Override
    public GadgetCollection getCollection() {
        return ropChain;
    }

    // MODIFIES: this
    // EFFECTS: Creates a list entry for the given exploitObject and adds it to the GUI.
    @Override
    public void insert(ExploitObject exploitObject) {
        getCollectionViewer().add(new ExploitObjectListItem(this, exploitObject));
        validate();
    }

    // EFFECTS: Saves this ropChain to the specified file.
    @Override
    void saveCollection() {
        if (showSaveDialog()) {
            try {
                new JsonWriter(getSelectedFile()).writeObject(ropChain);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not save ROP chain to file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // MODIFIES: this.ropChain, this.parentFrame, this
    // EFFECTS: Overwrites this ropChain with data from the specified file.
    @Override
    void loadCollection() {
        if (showOpenDialog()) {
            try {
                new JsonReader(getSelectedFile()).ropChainFromFile(ropChain);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error: could not load ROP chain from file " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        reload();
    }

    // MODIFIES: this.ropChain, this.parentFrame, this
    // EFFECTS: Prompts the user for a new name to replace the current ropChain name.
    //          If successful, renames the ropChain to the given string.
    @Override
    void renameCollection() {
        String name;

        do {
            name = (String) JOptionPane.showInputDialog(
                    null,
                    "New ROP chain name:\n",
                    "Rename ROP Chain",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    ropChain.getName()
            );

            if (name == null) {
                return;
            }
        } while (name.length() == 0);

        ropChain.setName(name);
        setTitleLabel(ropChain.getName());

        parentFrame.reload();
    }

    // MODIFIES: this.ropchain, this.parentFrame, this
    // EFFECTS: handles insertion ActionEvents or passes the ActionEvent to the CollectionEditorGui handler.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(insertPaddingItem)) {
            newPadding();
        } else if (source.equals(insertAddressItem)) {
            newAddressGadget();
        } else if (source.equals(insertInstructionsItem)) {
            newInstructionsGadget();
        } else if (source.equals(insertStringItem)) {
            newStringGadget();
        } else if (source.equals(insertSymbolItem)) {
            newSymbolGadget();
        } else {
            super.actionPerformed(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets ActionListeners for insert menu options and adds insert options to the insert menu.
    @Override
    void addInsertOptions(JMenu insertMenu) {
        insertPaddingItem.addActionListener(this);
        insertAddressItem.addActionListener(this);
        insertInstructionsItem.addActionListener(this);
        insertStringItem.addActionListener(this);
        insertSymbolItem.addActionListener(this);

        insertMenu.add(insertPaddingItem);
        insertMenu.add(insertAddressItem);
        insertMenu.add(insertInstructionsItem);
        insertMenu.add(insertStringItem);
        insertMenu.add(insertSymbolItem);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new Padding, prompts the user for values.
    private void newPadding() {
        new NewExploitElementDialog(
                this,
                new Padding(),
                "New Padding",
                Collections.singletonList(new Pair<>("Length", "8"))
        );
    }

    // MODIFIES: this
    // EFFECTS: Creates a new AddressGadget, prompts the user for values.
    private void newAddressGadget() {
        new NewExploitElementDialog(
                this,
                new AddressGadget(),
                "New Address Gadget",
                Arrays.asList(new Pair<>("Base", "exe.address"), new Pair<>("Offset", "0"))
        );
    }

    // MODIFIES: this
    // EFFECTS: Creates a new InstructionsGadget, prompts the user for values.
    private void newInstructionsGadget() {
        new NewInstructionsGadgetDialog(this, new InstructionsGadget());
    }

    // MODIFIES: this
    // EFFECTS: Creates a new StringGadget, prompts the user for values.
    private void newStringGadget() {
        new NewExploitElementDialog(
                this,
                new StringGadget(),
                "New String Gadget",
                Arrays.asList(new Pair<>("Base", "exe"), new Pair<>("String", "/bin/sh\\x00"))
        );
    }

    // MODIFIES: this
    // EFFECTS: Creates a new SymbolGadget, prompts the user for values.
    private void newSymbolGadget() {
        new NewExploitElementDialog(
                this,
                new SymbolGadget(),
                "New Symbol Gadget",
                Arrays.asList(new Pair<>("Base", "exe"), new Pair<>("Type", "sym"), new Pair<>("Symbol", "main"))
        );
    }
}
