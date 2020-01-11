import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size, maxSize;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        maxSize = 50;
        arr = (Item[]) new Object[50];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("arg is null in enqueue");
        if (size == maxSize -1) resize(maxSize * 2);
        arr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException("dequeue");
        int randIndex = StdRandom.uniform(0, size);
        Item temp = arr[randIndex];
        arr[randIndex] = arr[--size];
        arr[size] = null;   // set value to null becoz garbage collector then frees the memory, else mem not freed and loitering takes place, i.e mem still allocated and mem leak possible
        if (maxSize > 0 && size < maxSize / 4) {
            resize(maxSize /2);
        }
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("sample");
        }
        int randIndex = StdRandom.uniform(0, size);
        return arr[randIndex];
    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        System.arraycopy(arr, 0, temp, 0, size);
        arr = temp;
        maxSize = newSize;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int currentIndex = 0;
        private Item[] newarr;
        RandomizedQueueIterator() {
            currentIndex = 0;
            newarr = (Item[]) new Object[size];
            System.arraycopy(arr, 0, newarr, 0, size);
            StdRandom.shuffle(newarr);
        }
        public boolean hasNext() {
            return currentIndex != size;
        }
        public void remove() {
            throw new UnsupportedOperationException("remove method called");
        }
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException("next method called");
            }
            Item item = newarr[currentIndex];
            currentIndex++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq1 = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq1.enqueue(i);
        }
        /*
        Iterator itr = rq1.iterator();
        for (int i = 0; i < 10; i++) {
            //StdOut.println(rq1.sample());
            StdOut.println(itr.next());
        }
        StdOut.println();
        //StdOut.println(rq1.size);
        for (int i = 0; i < 10; i++) {
            StdOut.println(rq1.dequeue());
        }
         */
    }

}