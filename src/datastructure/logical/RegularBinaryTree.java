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
        children.enqueue(this.root);

        while (!children.isEmpty()) {
            TreeNode<T> node = children.dequeue();

            if (node != null) {
                if (node.getData().equals(element)) {
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
    public void remove(T element) {

        boolean found = false;

        Queue<TreeNode<T>> children = new DynamicQueue<>();
        children.enqueue(this.root);

        TreeNode<T> current = null;

        while (!children.isEmpty()) {
            current = children.dequeue();

            if (current != null) {
                if (current.getData().equals(element)) {
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
    public void add(T element) {

        TreeNode<T> newChild = new TreeNode<>(element);

        if (this.root == null) {
            this.root = newChild;
        }
        else {
            Queue<TreeNode<T>> children = new DynamicQueue<>();
            boolean found = false;

            children.enqueue(this.root);

            while ((!found) && (!children.isEmpty())) {
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

        if ((this.root.getLeft() == null) && (this.root.getRight() == null)) {
            this.root = null;
            return;
        }

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        TreeNode<T> currentNode = this.root;
        TreeNode<T> previousNode;

        queue.enqueue(this.root);

        while (!queue.isEmpty()) {
            previousNode = currentNode;
            currentNode = queue.dequeue();

            if (currentNode.getLeft() == null) {
                previousNode.setRight(null);
                break;
            }
            else if (currentNode.getRight() == null) {
                currentNode.setLeft(null);
                break;
            }
            else {
                queue.enqueue(currentNode.getLeft());
                queue.enqueue(currentNode.getRight());
            }
        }

        queue.clear();
    }

    private TreeNode<T> getDeepestNode() {

        Queue<TreeNode<T>> queue = new DynamicQueue<>();
        queue.enqueue(this.root);

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
