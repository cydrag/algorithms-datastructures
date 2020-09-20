package com.cydrag.datastructure.nodes;

import com.cydrag.datastructure.logical.HashTable;
import com.cydrag.datastructure.physical.LinkedList;

public class TrieNode {

    private final HashTable<Character, TrieNode> holder;
    private boolean endOfWord;

    public TrieNode() {
        this.holder = new HashTable<>();
        this.endOfWord = false;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void add(Character c) {
        this.holder.add(c, new TrieNode());
    }

    public TrieNode get(Character c) {
        return this.holder.get(c);
    }

    public LinkedList<Character> keys() {
        return this.holder.keys();
    }

    public int length() {
        return this.holder.size();
    }

    public String characters() {
        StringBuilder chars = new StringBuilder();

        for (Character c : this.holder.keys()) {
            chars.insert(0, c);
        }

        return chars.toString();
    }

    public void remove(Character c) {
        this.holder.remove(c);
    }

    public boolean isEmpty() {
        return this.holder.isEmpty();
    }

    public void clear() {
        for (Character c : this.holder.keys()) {
            TrieNode trieNode = this.holder.get(c);
            trieNode.setEndOfWord(false);
            trieNode.clear();
        }
        this.holder.clear();
        this.setEndOfWord(false);
    }
}
