package model;

import java.util.LinkedList;

public abstract class GadgetCollection implements ExploitElement {
    private final LinkedList<ExploitElement> exploitElementList;
    private String name;

    // EFFECTS: creates a new GadgetCollection with an empty list of ExploitElements
    public GadgetCollection() {
        exploitElementList = new LinkedList<>();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds ExploitElement to the ExploitElementList at the specified index, returns true if successful
    public boolean add(ExploitElement exploitElement, int index) {
        if (index <= exploitElementList.size()) {
            exploitElementList.add(index, exploitElement);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the ExploitElement at specified index, returns true if successful, otherwise returns false
    public boolean remove(int index) {
        if (index < exploitElementList.size()) {
            exploitElementList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // EFFECTS: returns the ExploitElement at the specified index or null if no such object exists
    public ExploitElement get(int index) {
        if (index < exploitElementList.size()) {
            return exploitElementList.get(index);
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the GadgetCollection
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of the GadgetCollection
    public String getName() {
        return name;
    }

    // EFFECTS: returns the list of ExploitElements
    public LinkedList<ExploitElement> getList() {
        return exploitElementList;
    }

    // EFFECTS: returns the number of elements in the exploitElementList
    public int getLength() {
        return exploitElementList.size();
    }

    // EFFECTS: returns a python command to produce the GadgetCollection
    public abstract String getScript();
}
