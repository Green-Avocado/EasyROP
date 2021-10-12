package model;

import java.util.LinkedList;

public abstract class GadgetCollection implements ExploitElement {
    private final LinkedList<ExploitElement> exploitElementList;

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

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the ExploitElement at the specified index with the specified ExploitElement,
    //          returns true if successful, otherwise returns false
    public boolean set(ExploitElement exploitElement, int index) {
        if (index < exploitElementList.size()) {
            exploitElementList.set(index, exploitElement);
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

    // EFFECTS: returns a string of Python commands to produce the script
    public String getScript() {
        String script = "";

        for (ExploitElement element : exploitElementList) {
            String s = element.getScript();

            if (!s.isEmpty()) {
                if (!script.isEmpty()) {
                    script = script.concat("\n");
                }

                script = script.concat(s);
            }
        }

        return script;
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

    // EFFECTS: returns the list of ExploitElements
    public LinkedList<ExploitElement> getList() {
        return exploitElementList;
    }

    // EFFECTS: returns the number of elements in the exploitElementList
    public int getLength() {
        return exploitElementList.size();
    }
}
