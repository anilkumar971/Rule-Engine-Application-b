package com.example.RuleEngineApplication.Model;


/*public class ASTNode {
    private String type;  // "operator" or "operand"
    private ASTNode left;
    private ASTNode right;
    private String value;  // Can hold both operator or operand based on the type

    // Constructor
    public ASTNode(String type, ASTNode left, ASTNode right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;  // Whether it's an operator or operand is handled by the type
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;  // This will hold the operand or operator based on the type
    }

    public void setValue(String value) {
        this.value = value;
    }

}*/
public class ASTNode {
    private String type;  // "operator" or "operand"
    private ASTNode left; // Left child
    private ASTNode right; // Right child
    private String value;  // Can hold both operator or operand based on the type

    // Constructor
    public ASTNode(String type, ASTNode left, ASTNode right, String value) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = value;  // Whether it's an operator or operand is handled by the type
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;  // This will hold the operand or operator based on the type
    }

    public void setValue(String value) {
        this.value = value;
    }

}