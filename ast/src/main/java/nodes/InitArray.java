package nodes;

public class InitArray implements Node {
    private Name name;
    private String arrayType;
    private ArrayLiteral arrayLiteral;

    public InitArray(Name name, ArrayLiteral arrayLiteral) {
        this.name = name;
        this.arrayLiteral = arrayLiteral;
    }

    public InitArray(Name name, String arrayType, ArrayLiteral arrayLiteral) {
        this.name = name;
        this.arrayType = arrayType;
        this.arrayLiteral = arrayLiteral;
    }

    public Name getName() {
        return name;
    }

    public String getArrayType() {
        return arrayType;
    }

    public ArrayLiteral getArrayLiteral() {
        return arrayLiteral;
    }
}
