package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Represents a collection of Gadgets or a collection of collections.
public abstract class GadgetCollection implements ExploitObject {
    protected final LinkedList<ExploitObject> exploitObjectList;
    private String name;

    // EFFECTS: Creates a new GadgetCollection with an empty list of ExploitObjects.
    protected GadgetCollection() {
        exploitObjectList = new LinkedList<>();
    }

    // REQUIRES: list.size() == 1
    // MODIFIES: this
    // EFFECTS: sets the name of this object to the element in the list.
    @Override
    public void fromList(List<String> list) {
        setName(list.get(0));
    }

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: Adds ExploitObject to the ExploitObjectList at the specified index, returns true if successful.
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
    // EFFECTS: Removes the ExploitObject at specified index, returns true if successful, otherwise returns false.
    public boolean remove(int index) {
        if (index < exploitObjectList.size()) {
            exploitObjectList.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all elements from the exploitObjectList.
    public void clear() {
        exploitObjectList.clear();
    }

    // REQUIRES index >= 0
    // EFFECTS: Returns the ExploitObject at the specified index or null if no such object exists.
    public ExploitObject get(int index) {
        if (index < exploitObjectList.size()) {
            return exploitObjectList.get(index);
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the name of the GadgetCollection.
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: Returns the name of the GadgetCollection.
    @Override
    public String getName() {
        return name;
    }

    // EFFECTS: Returns the list of ExploitObject.
    public List<ExploitObject> getList() {
        return Collections.unmodifiableList(exploitObjectList);
    }

    // EFFECTS: Returns the number of elements in the exploitObjectList.
    public int getLength() {
        return exploitObjectList.size();
    }

    // EFFECTS: Returns a python command to produce the GadgetCollection.
    @Override
    public String getScript() {
        StringBuilder script = new StringBuilder();

        for (ExploitObject element : getList()) {
            script.append(getScriptInternal(element));
        }

        script.insert(0, " = ''");
        script.insert(0, getName());

        return script.toString();
    }

    // EFFECTS: Returns a python command to produce the element.
    abstract String getScriptInternal(ExploitObject element);
}
