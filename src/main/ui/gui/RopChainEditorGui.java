package ui.gui;

import javafx.util.Pair;
import model.ExploitObject;
import model.GadgetCollection;
import model.RopChain;
import model.gadgets.*;
import persistence.JsonReader;
import persistence.JsonWriter;

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

    // EFFECTS: Creates a new RopChainEditorGui.
    public RopChainEditorGui(PayloadEditorGui parentFrame, RopChain ropChain) {
        this.parentFrame = parentFrame;
        this.ropChain = ropChain;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        super.init();
    }

    @Override
    GadgetCollection getCollection() {
        return ropChain;
    }

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

    @Override
    void insert(ExploitObject exploitObject) {
        getCollectionViewer().add(new ExploitObjectListItem(this, exploitObject));
        validate();
    }

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

    private void newPadding() {
        new NewExploitElementDialog(
                this,
                new Padding(),
                "New Padding",
                Collections.singletonList(new Pair<>("Length", "8"))
        );
    }

    private void newAddressGadget() {
        new NewExploitElementDialog(
                this,
                new AddressGadget(),
                "New Address Gadget",
                Arrays.asList(new Pair<>("Base", "exe.address"), new Pair<>("Offset", "0"))
        );
    }

    private void newInstructionsGadget() {
        new NewInstructionsGadgetDialog(this, new InstructionsGadget());
    }

    private void newStringGadget() {
        new NewExploitElementDialog(
                this,
                new StringGadget(),
                "New String Gadget",
                Arrays.asList(new Pair<>("Base", "exe"), new Pair<>("String", "/bin/sh\\x00"))
        );
    }

    private void newSymbolGadget() {
        new NewExploitElementDialog(
                this,
                new SymbolGadget(),
                "New Symbol Gadget",
                Arrays.asList(new Pair<>("Base", "exe"), new Pair<>("Type", "sym"), new Pair<>("Symbol", "main"))
        );
    }
}
