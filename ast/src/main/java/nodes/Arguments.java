package nodes;

import java.util.ArrayList;

public class Arguments implements Node{
    private ArrayList<Node> exps;

    public Arguments(ArrayList<Node> exps) {
        this.exps = exps;
    }

    public ArrayList<Node> getExps() {
        return exps;
    }
}
