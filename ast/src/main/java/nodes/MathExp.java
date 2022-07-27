package nodes;

public class MathExp implements Node {
    Node leftOperand;
    Node rightOperand;
    String operator;

    public MathExp(String operator) {
        this.operator = operator;
    }

    public Node getLeftOperand() {
        return leftOperand;
    }

    public Node getRightOperand() {
        return rightOperand;
    }

    public void setLeftOperand(Node leftOperand) {
        this.leftOperand = leftOperand;
    }

    public void setRightOperand(Node rightOperand) {
        this.rightOperand = rightOperand;
    }

    public String getOperator() {
        return operator;
    }
}
