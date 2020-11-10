import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private ElementDeque firstElement = null;
    private ElementDeque lastElement = null;
    private int lengthDeque = 0;

    private class ElementDeque {
        private Item valueElement;
        private ElementDeque nextElement;
        private ElementDeque prevElement;
    }

    private class DequeIterator implements Iterator<Item> {
        private ElementDeque currentElement = firstElement;

        public boolean hasNext() {
            return currentElement != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Item item = currentElement.valueElement;
                currentElement = currentElement.nextElement;
                return item;
            }
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.lengthDeque == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.lengthDeque;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item not null");
        ElementDeque oldFirstElement = this.firstElement;
        this.firstElement = new ElementDeque();
        this.firstElement.valueElement = item;
        this.firstElement.nextElement = oldFirstElement;
        this.firstElement.prevElement = null;
        if (isEmpty()) this.lastElement = this.firstElement;
        else oldFirstElement.prevElement = this.firstElement;
        this.lengthDeque++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item not null");
        ElementDeque oldLastElement = this.lastElement;
        this.lastElement = new ElementDeque();
        this.lastElement.valueElement = item;
        this.lastElement.nextElement = null;
        this.lastElement.prevElement = oldLastElement;
        if (isEmpty()) this.firstElement = this.lastElement;
        else oldLastElement.nextElement = this.lastElement;
        this.lengthDeque++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item valueElement = this.firstElement.valueElement;
        this.lengthDeque--;
        if (isEmpty()) {
            this.firstElement = null;
            this.lastElement = null;
        } else {
            this.firstElement = this.firstElement.nextElement;
            this.firstElement.prevElement = null;
        }
        return valueElement;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        Item valueElement = this.lastElement.valueElement;
        this.lengthDeque--;
        if (isEmpty()) {
            this.firstElement = null;
            this.lastElement = null;
        } else {
            this.lastElement = this.lastElement.prevElement;
            this.lastElement.nextElement = null;
        }
        return valueElement;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dTest = new Deque<>();
        dTest.addFirst(1);
        dTest.addFirst(10);
        dTest.addFirst(100);
        dTest.addFirst(1000);
        dTest.addLast(10000);
        StdOut.println(dTest.size());
        StdOut.println(dTest.removeLast());
        StdOut.println(dTest.size());
        // for (int _data : _d) System.out.println(_data);
        // System.out.println(_d.size());
    }
}
