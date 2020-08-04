package datastructure.logical;

public class BinarySearchTree<T extends Comparable<? super T>> extends DynamicBinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(T data) {
        super(data);
    }

    @Override
    public boolean contains(T element) {
        return search(super.root, element);
    }

    @Override
    public void remove(T element) {
        super.root = this.erase(super.root, element);
    }

    @Override
    public void add(T element) {
        super.root = this.insert(super.root, element);
    }

    private boolean search(TreeNode<T> currentNode, T dataToSearch) {
        if (currentNode == null) {
            return false;
        }
        else if (currentNode.getData().compareTo(dataToSearch) == 0) {
            return true;
        }
        else if (currentNode.getData().compareTo(dataToSearch) > 0) {
            return search(currentNode.getLeft(), dataToSearch);
        }
        else {
            return search(currentNode.getRight(), dataToSearch);
        }
    }

    private TreeNode<T> erase(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return null;
        }
        else if (currentNode.getData().compareTo(data) > 0) {
            currentNode.setLeft(erase(currentNode.getLeft(), data));
        }
        else if (currentNode.getData().compareTo(data) < 0) {
            currentNode.setRight(erase(currentNode.getRight(), data));
        }
        else {
            if ((currentNode.getLeft() != null) && (currentNode.getRight() != null)) {
                TreeNode<T> minRightNode = minimumNode(currentNode.getRight());
                currentNode.setData(minRightNode.getData());
                currentNode.setRight(erase(currentNode.getRight(), minRightNode.getData()));
            }
            else if (currentNode.getLeft() == null) {
                currentNode = currentNode.getRight();
            }
            else if (currentNode.getRight() == null) {
                currentNode = currentNode.getLeft();
            }
            else {
                currentNode = null;
            }
        }

        return currentNode;
    }

    private static <T> TreeNode<T> minimumNode(TreeNode<T> root) {
        if (root.getLeft() == null) {
            return root;
        }

        return minimumNode(root.getLeft());
    }

    private TreeNode<T> insert(TreeNode<T> currentNode, T data) {
        if (currentNode == null) {
            return new TreeNode<>(data);
        }
        else if (currentNode.getData().compareTo(data) >= 0) {
            currentNode.setLeft(insert(currentNode.getLeft(), data));
            return currentNode;
        }
        else {
            currentNode.setRight(insert(currentNode.getRight(), data));
            return currentNode;
        }
    }
}
