package nodes;

import java.util.ArrayList;

public class Parameters implements Node{
    private ArrayList<Name> names;
    private ArrayList<String> types;

    public Parameters(ArrayList<Name> names, ArrayList<String> types) {
        this.names = names;
        this.types = types;
    }

    public ArrayList<Name> getNames() {
        return names;
    }

    public ArrayList<String> getDataTypes() {
        return types;
    }
}
