import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] elements;
    private int lengthElements;

    private class RandomIterator implements Iterator<Item> {
        private int count;
        private final int[] randomIndex;
        public RandomIterator() {
            count = 0;
            randomIndex = new int[lengthElements];
            for (int i = 0; i < lengthElements; i++) randomIndex[i] = i;
            StdRandom.shuffle(randomIndex);
        }

        @Override
        public boolean hasNext() {
            return count < lengthElements;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[randomIndex[count++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public RandomizedQueue() {
        this.elements = (Item []) new Object[1];
        this.lengthElements = 0;
    }

    public boolean isEmpty() {
        return this.lengthElements == 0;
    }

    public int size() {
        return this.lengthElements;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (this.lengthElements == this.elements.length) resize(2 * this.elements.length);
        this.elements[this.lengthElements++] = item;
    }

    private void resize(int capacity) {
        Item[] tempElement = (Item[]) new Object[capacity];
        for (int i = 0; i < this.lengthElements; i++) {
            tempElement[i] = this.elements[i];
        }
        this.elements = tempElement;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (this.lengthElements == this.elements.length / 4) resize(this.elements.length / 2);
        int random = StdRandom.uniform(this.lengthElements);
        Item itemDeq = this.elements[random];
        this.elements[random] = this.elements[--this.lengthElements];
        this.elements[this.lengthElements] = null; // to prevent loitering
        return itemDeq;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.elements[StdRandom.uniform(this.lengthElements)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // Unit testing
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++) queue.enqueue(i);

        for (int a : queue) {
            for (int b : queue) StdOut.print(a + "-" + b + " ");
            // StdOut.print(a);
            StdOut.println();
        }
    }
}
