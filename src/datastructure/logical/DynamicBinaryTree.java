package datastructure.logical;

import datastructure.exceptions.NullValueException;
import datastructure.physical.LinkedList;
import datastructure.physical.SingleLinkedList;

abstract class DynamicBinaryTree<T> implements BinaryTree<T> {

    TreeNode<T> root;

    DynamicBinaryTree() {
        this.root = null;
    }

    DynamicBinaryTree(T root) {
        checkIfNull(root);
        this.root = new TreeNode<>(root);
    }

    void checkIfNull(T element) {
        if (element == null) {
            throw new NullValueException();
        }
    }

    @Override
    public boolean isFull() {
        if (this.root == null) {
            return false;
        }
        else {
            Queue<TreeNode<T>> queue = new DynamicQueue<>();

            queue.enqueue(this.root);
            int expected = 1; // broj dece koji se ocekiva na jednom nivou ukoliko svaki roditelj na istom nivou ima dece

            while (!queue.isEmpty()) {

                boolean firstHasChildren = true, first = true;

                for (int i = 0; i < expected; i++) {
                    TreeNode<T> current = queue.dequeue();

                    if (first) { // Uvek se prvi put na novom nivou pita roditelj da li ima dece
                        if ((current.getLeft() == null) && (current.getRight() == null)) { // ako nema onda se ocekuje da sledeci roditelji na istom nivou nemaju decu
                            firstHasChildren = false;
                        }
                        else if ((current.getLeft() != null) && (current.getRight() != null)) { // ako ima onda svi roditelji na istom nivou moraju da imaju decu
                            queue.enqueue(current.getLeft());
                            queue.enqueue(current.getRight());
                            firstHasChildren = true;
                        }
                        else { // Ukoliko bar jedan roditelj ima samo jedno dete, automatski nije potpuno stablo
                            queue.clear();
                            return false;
                        }
                        first = false;
                    }
                    else { // Sudbina potpunog stabla zavisi od prvog roditelja
                        if ((current.getLeft() != null && current.getRight() != null)) {
                            if (!firstHasChildren) {
                                queue.clear();
                                return false;
                            }
                            queue.enqueue(current.getLeft());
                            queue.enqueue(current.getRight());
                        }
                        else {
                            if (firstHasChildren) {
                                queue.clear();
                                return false;
                            }
                        }
                    }
                }

                expected *= 2;
            }

            return true;
        }
    }

    @Override
    public boolean isStrict() {
        if (this.root != null) {

            boolean strict = true;

            Queue<TreeNode<T>> nodes = new DynamicQueue<>();
            nodes.enqueue(this.root);

            while (!nodes.isEmpty()) {
                TreeNode<T> currentNode = nodes.dequeue();

                if (!(((currentNode.getLeft() == null) && (currentNode.getRight() == null)) ||
                        ((currentNode.getLeft() != null) && (currentNode.getRight() != null)))) {
                    strict = false;
                    break;
                }
                else {
                    if (currentNode.getLeft() != null) {
                        nodes.enqueue(currentNode.getLeft());
                    }

                    if (currentNode.getRight() != null) {
                        nodes.enqueue(currentNode.getRight());
                    }
                }
            }
            nodes.clear();
            return strict;
        }
        return false;
    }

    @Override
    public LinkedList<T> inOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.inOrder(linkedList, this.root);
        return linkedList;
    }

    private void inOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            this.inOrder(list, root.getLeft());
            list.addAtEnd(root.getData());
            this.inOrder(list, root.getRight());
        }
    }

    @Override
    public LinkedList<T> preOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.preOrder(linkedList, this.root);
        return linkedList;
    }

    private void preOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            list.addAtEnd(root.getData());
            this.preOrder(list, root.getLeft());
            this.preOrder(list, root.getRight());
        }
    }

    @Override
    public LinkedList<T> postOrder() {
        LinkedList<T> linkedList = new SingleLinkedList<>();
        this.postOrder(linkedList, this.root);
        return linkedList;
    }

    private void postOrder(LinkedList<T> list, TreeNode<T> root) {
        if (root != null) {
            this.postOrder(list, root.getLeft());
            this.postOrder(list, root.getRight());
            list.addAtEnd(root.getData());
        }
    }

    @Override
    public LinkedList<T> levelOrder() {

        LinkedList<T> linkedList = new SingleLinkedList<>();
        Queue<TreeNode<T>> queue = new DynamicQueue<>();

        if (this.root != null) {
            queue.enqueue(this.root);
        }

        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.dequeue();

            linkedList.addAtEnd(node.getData());

            if (node.getLeft() != null) {
                queue.enqueue(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.enqueue(node.getRight());
            }
        }

        return linkedList;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    // TODO: Razmisliti o ovom dizajnu
    @Override
    public void add(T element) {
        this.checkIfNull(element);
        this.addElement(element);
    }

    @Override
    public void remove(T element) {
        this.checkIfNull(element);
        this.removeElement(element);
    }

    @Override
    public boolean contains(T element) {
        this.checkIfNull(element);
        return this.containsElement(element);
    }

    protected abstract void addElement(T element);
    protected abstract void removeElement(T element);
    protected abstract boolean containsElement(T element);
}
