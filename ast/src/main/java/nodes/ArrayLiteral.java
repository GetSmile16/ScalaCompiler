package nodes;

import java.util.ArrayList;

public class ArrayLiteral implements Node{
    private ArrayList<Node> exps;

    public ArrayLiteral(ArrayList<Node> exps) {
        this.exps = exps;
    }

    public ArrayList<Node> getExps() {
        return exps;
    }
}
