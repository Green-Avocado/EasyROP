package model;

import java.util.LinkedList;

public abstract class GadgetCollection implements ExploitObject {
    private final LinkedList<ExploitObject> exploitObjectList;
    private String name;

    // EFFECTS: creates a new GadgetCollection with an empty list of ExploitObjects
    protected GadgetCollection() {
        exploitObjectList = new LinkedList<>();
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: adds ExploitObject to the ExploitObjectList at the specified index, returns true if successful
    public boolean add(ExploitObject exploitObject, int index) {
        if (index <= exploitObjectList.size()) {
            exploitObjectList.add(index, exploitObject);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: index >= 0
    // MODIFIES: this
    // EFFECTS: removes the ExploitObject at specified index, returns true if successful, otherwise returns false
    public boolean remove(int index) {
        if (index < exploitObjectList.size()) {
            exploitObjectList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES index >= 0
    // EFFECTS: returns the ExploitObject at the specified index or null if no such object exists
    public ExploitObject get(int index) {
        if (index < exploitObjectList.size()) {
            return exploitObjectList.get(index);
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

    // EFFECTS: returns the list of ExploitObject
    public LinkedList<ExploitObject> getList() {
        return exploitObjectList;
    }

    // EFFECTS: returns the number of elements in the exploitObjectList
    public int getLength() {
        return exploitObjectList.size();
    }

    // EFFECTS: returns a python command to produce the GadgetCollection
    public String getScript() {
        StringBuilder script = new StringBuilder();

        for (ExploitObject element : getList()) {
            String s = element.getScript();

            if (!s.isEmpty()) {
                script.append(getScriptInternal(element));
            }
        }

        if (script.length() > 0) {
            script.insert(0, " = ''");
            script.insert(0, getName());

            return script.toString();
        } else {
            return "";
        }
    }

    protected abstract String getScriptInternal(ExploitObject element);
}
