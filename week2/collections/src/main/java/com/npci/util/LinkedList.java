package com.npci.util;

/*

    collection + iterator = iterable
    collection=> group of objects
    iterator => traverse the collection

 */

import java.util.Iterator;

// space & time complexity
public class LinkedList<E> implements Iterable<E> {
    private class Node {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    public void add(E data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }
    public void add(int index, E data) {

        Node newNode = new Node(data);
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (tail == null) {
                tail = head;
            }
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.getNext();
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) {
            tail = newNode;
        }

    }
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    public E get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative");
        }
        Node current = head;
        for (int i = 0; i < index && current != null; i++) {
            current = current.getNext();
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return current.getData();
    }
    public Iterator<E> iterator() {

        class It implements Iterator {
            Node current = head;
            public boolean hasNext() {
                return current != null;
            }

            public Object next() {
                if (current == null) {
                    throw new java.util.NoSuchElementException("No more elements in the list");
                }
                E data = current.getData();
                current = current.getNext();
                return data;
            }
        }
        It it = new It();
        return it;
    }
}
