package datastructure.logical;

public class RegularBinaryTree<T> extends DynamicBinaryTree<T> {

    public RegularBinaryTree() {
        super();
    }

    public RegularBinaryTree(T root) {
        super(root);
    }

    @Override
    public boolean contains(T data) {
        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(super.root);

        while (!children.isEmpty()) {
            TreeNode<T> node = children.dequeue();

            if (node != null) {
                if (node.getData().equals(data)) {
                    children.clear();
                    return true;
                }
                else {
                    children.enqueue(node.getLeft());
                    children.enqueue(node.getRight());
                }
            }
        }

        return false;
    }

    @Override
    public void remove(T data) {

        boolean found = false;

        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(super.root);

        TreeNode<T> current = null;

        while (!children.isEmpty()) {
            current = children.dequeue();

            if (current != null) {
                if (current.getData().equals(data)) {
                    found = true;
                    children.clear();
                    break;
                }
                else {
                    children.enqueue(current.getLeft());
                    children.enqueue(current.getRight());
                }
            }
        }

        if (found) {
            TreeNode<T> deepestNode = this.getDeepestNode();
            current.setData(deepestNode.getData());
            this.removeLastChild();
        }
    }

    @Override
    public void addChild(T data) {

        TreeNode<T> newChild = new TreeNode<>(data);

        if (super.root == null) {
            super.root = newChild;
        }
        else {
            Queue<TreeNode<T>> children = new DynamicQueue<>();
            boolean found = false;

            children.enqueue(super.root);

            while (!found) {
                TreeNode<T> node = children.dequeue();

                if (node.getLeft() == null) {
                    node.setLeft(newChild);
                    found = true;
                }
                else if (node.getRight() == null) {
                    node.setRight(newChild);
                    found = true;
                }
                else {
                    children.enqueue(node.getLeft());
                    children.enqueue(node.getRight());
                }
            }
            children.clear();
        }
    }

    private void removeLastChild() {

        // Check if root is null

        if ((super.root.getLeft() == null) && (super.root.getRight() == null)) {
            super.root = null;
            return;
        }

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        TreeNode<T> currentNode = super.root, previousNode;

        queue.enqueue(super.root);

        while (!queue.isEmpty()) {
            previousNode = currentNode;
            currentNode = queue.dequeue();

            if (currentNode.getLeft() == null) {
                previousNode.setRight(null);
                queue.clear();
                return;
            }
            else if (currentNode.getRight() == null) {
                currentNode.setLeft(null);
                queue.clear();
                return;
            }
            else {
                queue.enqueue(currentNode.getLeft());
                queue.enqueue(currentNode.getRight());
            }
        }
    }

    private TreeNode<T> getDeepestNode() {

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        queue.enqueue(super.root);

        TreeNode<T> current = null;

        while (!queue.isEmpty()) {
            current = queue.dequeue();

            if (current.getLeft() != null) {
                queue.enqueue(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.enqueue(current.getRight());
            }

        }

        return current;
    }



}
