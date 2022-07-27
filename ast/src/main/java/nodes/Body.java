package nodes;

import java.util.ArrayList;

public class Body implements Node{
    private ArrayList<Action> actions;

    public Body(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
