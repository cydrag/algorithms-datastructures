package com.cydrag.datastructure.logical;

import com.cydrag.datastructure.exceptions.NullValueException;
import com.cydrag.datastructure.nodes.TrieNode;
import com.cydrag.datastructure.physical.LinkedList;
import com.cydrag.datastructure.physical.SinglyLinkedList;

public class Trie implements Tree<String> {

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    private void checkIfNull(String value) {
        if (value == null) {
            throw new NullValueException();
        }
    }

    public boolean hasWord(String value) {
        this.checkIfNull(value);
        char[] characters = value.toCharArray();

        TrieNode current = this.root;

        for (Character c : characters) {
            TrieNode temp = current.get(c);

            if (temp == null) {
                return false;
            }
            current = temp;
        }

        return current.isEndOfWord();
    }

    @Override
    public LinkedList<String> levelOrder() {
        LinkedList<String> linkedList = new SinglyLinkedList<>();
        TrieNode current = this.root;

        Queue<Character> charsQueue = new DynamicQueue<>();
        Queue<TrieNode> trieNodesQueue = new DynamicQueue<>();

        if (!current.isEmpty()) {
            for (Character c : current.keys()) {
                charsQueue.add(c);
                trieNodesQueue.add(current);
            }

            linkedList.add(current.characters());

            while (!charsQueue.isEmpty()) {
                Character c = charsQueue.dequeue();
                current = trieNodesQueue.dequeue().get(c);

                if (current != null) {
                    for (Character chr : current.keys()) {
                        charsQueue.add(chr);
                        trieNodesQueue.add(current);
                    }
                    if (!current.characters().equals("")) {
                        linkedList.add(current.characters());
                    }
                }
            }
            trieNodesQueue.clear();
        }

        linkedList.addAtStart("");
        return linkedList;
    }

    private void traverseWord(TrieNode current, LinkedList<String> list, StringBuilder sb) {

        if ((current == null) || (current.isEmpty())) {
            if (sb.length() > 0) {
                list.addAtEnd(sb.toString());
            }
        }
        else {
            if (current.isEndOfWord()) {
                list.addAtEnd(sb.toString());
            }
            for (Character c : current.keys()) {
                sb.append(c);
                traverseWord(current.get(c), list, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public LinkedList<String> words() {
        LinkedList<String> words = new SinglyLinkedList<>();
        traverseWord(this.root, words, new StringBuilder());

        return words;
    }

    @Override
    public void add(String value) {
        this.checkIfNull(value);

        char[] characters = value.toCharArray();

        TrieNode current = this.root;

        for (Character c : characters) {
            TrieNode temp = current.get(c);

            if (temp == null) {
                current.add(c);
            }
            current = current.get(c);
        }

        current.setEndOfWord(true);
    }

    @Override
    public void remove(String value) {
        this.checkIfNull(value);

        if (this.hasWord(value)) {
            this.remove(this.root, value, 0);
        }
    }

    private boolean remove(TrieNode previous, String word, int index) {

        if ((previous == this.root) && (word.equals(""))) {
            previous.setEndOfWord(false);
            return true;
        }

        Character c = word.charAt(index);
        TrieNode current = previous.get(c);

        if (current.length() > 1) {
            this.remove(current, word, index + 1);
            return false;
        }

        if (index == word.length() - 1) {
            if (current.length() >= 1) {
                current.setEndOfWord(false);
                return false;
            }
            else {
                previous.remove(c);
                return true;
            }
        }

        if (current.isEndOfWord()) {
            this.remove(current, word, index + 1);
            return false;
        }

        boolean canBeRemoved = this.remove(current, word, index + 1);

        if (canBeRemoved) {
            previous.remove(c);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean contains(String value) {
        this.checkIfNull(value);

        char[] characters = value.toCharArray();

        TrieNode current = this.root;

        for (Character c : characters) {
            TrieNode temp = current.get(c);

            if (temp == null) {
                return false;
            }
            else {
                current = current.get(c);
            }
        }

        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override
    public int size() {
        return this.levelOrder().size();
    }

    @Override
    public void clear() {
        this.root.clear();
    }
}
