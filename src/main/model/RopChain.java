package model;

public class RopChain extends GadgetCollection {
    // EFFECTS: creates a new RopChain with an empty list of gadgets
    public RopChain() {
        super();
        setName("ropChain");
    }

    // EFFECTS: returns a string of Python commands to produce the RopChain
    public String getScript() {
        StringBuilder script = new StringBuilder();
        for (ExploitElement element : getList()) {
            String s = element.getScript();

            if (!s.isEmpty()) {
                script.append('\n');
                script.append(getName());
                script.append(" += ");
                script.append(s);
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

    // REQUIRES index >= 0
    // MODIFIES: this
    // EFFECTS: replaces the ExploitElement at the specified index with the specified ExploitElement,
    //          returns true if successful, otherwise returns false
    public boolean set(ExploitElement gadget, int index) {
        if (index < getList().size()) {
            getList().set(index, gadget);
            return true;
        } else {
            return false;
        }
    }
}
