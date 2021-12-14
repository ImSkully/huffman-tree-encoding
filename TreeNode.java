/*
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
*/

public class TreeNode {
    private Object item;
    private TreeNode parentNode;
    private TreeNode leftChild;
    private TreeNode rightChild;

    // Constructor.
    public TreeNode(Object newItem) {
        // Initializes tree node with item and no children.
        item = newItem;
        parentNode = null;
        leftChild = null;
        rightChild = null;
    }

    // Constructor with parent and children.
    public TreeNode(Object newItem, TreeNode left, TreeNode right, TreeNode parent) {
        // Initializes tree node with item and the left and right children references.
        item = newItem;
        parentNode = parent;
        leftChild = left;
        rightChild = right;
    }

    // Getters.
    public Object getItem() {
        return item;
    }

    public TreeNode getParent() {
        return this.parentNode;
    }

    public TreeNode getLeft() {
        return leftChild;
    }

    public TreeNode getRight() {
        return rightChild;
    }

    // Setters.
    public void setItem(Object newItem) {
        item = newItem;
    }

    public void setParent(TreeNode parent) {
        this.parentNode = parent;
    }

    public void setLeft(TreeNode left) {
        leftChild = left;
    }

    public void setRight(TreeNode right) {
        rightChild = right;
    }
}