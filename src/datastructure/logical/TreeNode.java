package datastructure.logical;

class TreeNode<T> {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    TreeNode(T data) {
       this.data = data;
       this.left = this.right = null;
    }

    T getData() {
       return data;
    }

    void setData(T data) {
        this.data = data;
    }

    TreeNode<T> getLeft() {
       return left;
    }

    void setLeft(TreeNode<T> left) {
       this.left = left;
    }

    TreeNode<T> getRight() {
       return right;
    }

    void setRight(TreeNode<T> right) {
       this.right = right;
    }
}
