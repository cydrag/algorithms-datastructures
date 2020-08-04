package datastructure.logical;

public class RegularBinaryTree<T> extends DynamicBinaryTree<T> {

    public RegularBinaryTree() {
        super();
    }

    public RegularBinaryTree(T root) {
        super(root);
    }

    @Override
    public boolean contains(T element) {
        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(super.root);

        while (!children.isEmpty()) {
            TreeNode<T> node = children.dequeue();

            if (node != null) {
                if (node.getData().equals(element)) {
                    children.destroy();
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
    public void remove(T element) {

        boolean found = false;

        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(super.root);

        TreeNode<T> current = null;

        while (!children.isEmpty()) {
            current = children.dequeue();

            if (current != null) {
                if (current.getData().equals(element)) {
                    found = true;
                    children.destroy();
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
    public void add(T element) {

        TreeNode<T> newChild = new TreeNode<>(element);

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
            children.destroy();
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
                queue.destroy();
                return;
            }
            else if (currentNode.getRight() == null) {
                currentNode.setLeft(null);
                queue.destroy();
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
